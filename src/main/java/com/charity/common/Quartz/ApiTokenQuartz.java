package com.charity.common.Quartz;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.entity.canteen.Order;
import com.charity.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ApiTokenQuartz {

    private final static String ADD_ORDER = "http://open.printcenter.cn:8080/addOrder";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderService orderService;

    //    每天8分钟重置token
    @Scheduled(cron = "0 */8 * * * ?")
    public void setToken(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                ValueOperations valueOperations = redisTemplate.opsForValue();
                Object apiToken = valueOperations.get("apiToken");
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                valueOperations.set("apiToken",uuid,600,TimeUnit.SECONDS);
                valueOperations.set("apiTokenOld",apiToken.toString(),120,TimeUnit.SECONDS);
                System.out.println("新的token："+uuid);
            }
        };
        thread.start();
    }
    //    每天10分钟重置token
    @Scheduled(cron = "0 */10 9-11 * * ?")
    public void sendPrintCenter(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                ValueOperations valueOperations = redisTemplate.opsForValue();
                String list = valueOperations.get("errorPrintCenterList").toString();
                JSONArray array = JSONArray.parseArray(list);
                Iterator<Object> iterator = array.iterator();
                while (iterator.hasNext()){
                    String next = iterator.next().toString();
                    JSONObject jsonObject = JSONObject.parseObject(next);
                    String resp = HttpClientUtil.sendHttpPost(ADD_ORDER, jsonObject.toJavaObject(Map.class));
                    JSONObject object = JSONObject.parseObject(resp);
                    if (object!=null&&Integer.parseInt(object.get("responseCode").toString())<9) {
                        iterator.remove();
                    }
                }
            }
        };
        thread.start();
    }

    //    每天12点00分取消订单
    @Scheduled(cron = "0 00 12 * * ?")
    public void changeOrderStatus(){
        Map where = new HashMap();
        where.put("status",1);
        PageInfo<Order> pageInfo = orderService.getOrderList(where);
        List<Order> orderList = pageInfo.getList();
        orderList.stream().forEach(order -> {
            order.setStatus(-1);
            order.setModifyTime(new Date());
            orderService.updateSelective(order);
        });
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("errorPrintCenterList", "");
    }
}
