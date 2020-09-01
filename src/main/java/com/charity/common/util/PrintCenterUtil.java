package com.charity.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.charity.entity.canteen.Order;
import com.charity.entity.canteen.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JiaoZhipeng
 * @date 2018/10/23 16:57
 **/
@Component
public class PrintCenterUtil {

    private final static String ADD_ORDER = "http://open.printcenter.cn:8080/addOrder";
    private final static String QUERY_ORDER = "http://open.printcenter.cn:8080/queryOrder";
    private final static String QUERY_PRINTER_STATUS = "http://open.printcenter.cn:8080/queryPrinterStatus";

    @Autowired
    private RedisTemplate redisTemplate;

    public void addOrder(Order order){
        Map<String,String> map = new HashMap<>( );
        map.put("deviceNo","kdt2200235");
        map.put("key","72a8b");
        String printContent = "<A2>----------------</END>";
        printContent += "<A2> 房山科技产业园 </END>";
        printContent += "</END>";
        printContent += "<H2>    餐厅配送 </END>";
        printContent += "<A2> </END>";
        printContent += "订单号:1538016034890335</END>";
        printContent += "</END>";
        printContent += "<H2> --已在线支付--</END>";
        printContent += "</END>";
        printContent += "下单时间："+ order.getCreateTime() +"</END>";
        printContent += "</END>";
        printContent += "<H2>---订餐内容：---</END>";
        printContent += "</END>";
        List<OrderDetail> orderDetailList = order.getOrderDetailList();
        for (int i = 0; i < orderDetailList.size() ; i++) {
            printContent += (i+1) + "." + orderDetailList.get(i).getCname() + "   ×" + orderDetailList.get(i).getNum() +"份  "+ orderDetailList.get(i).getPrice() +"元</END>";
        }
        printContent += "配送费："+order.getTransportFee()+"元</END>";
        printContent += "餐盒费："+order.getBoxFee()+"元</END>";
        printContent += "<H2>总计：   "+order.getAmt()+"元</END>";
        printContent += "</END>";
        printContent += "<H2>---订餐人信息---</END>";
        printContent += "</END>";
        printContent += "<H2>地址："+order.getAddress()+"</END>";
        printContent += "<H2>订餐人："+order.getUserName()+"</END>";
        printContent += "<H2>联系方式："+order.getPhone()+"</END>";
        printContent += "<H2>----------------</END>";
        map.put("printContent",printContent);
        map.put("times","1");
        String resp = HttpClientUtil.sendHttpPost(ADD_ORDER,map);
        JSONObject jsonObject = JSONObject.parseObject(resp);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (jsonObject!=null&&Integer.parseInt(jsonObject.get("responseCode").toString())>9) {
            resp = HttpClientUtil.sendHttpPost(ADD_ORDER, map);
            jsonObject = JSONObject.parseObject(resp);
            if (jsonObject != null && Integer.parseInt(jsonObject.get("responseCode").toString()) > 9) {
                String list = valueOperations.get("errorPrintCenterList").toString();
                JSONArray array = JSONArray.parseArray(list);
                array.add(map.toString());
                valueOperations.set("errorPrintCenterList", array);
            }
        }/*else {
            String orderindex = jsonObject.get("orderindex").toString();
            String list = valueOperations.get("successPrintCenterList").toString();
            JSONArray array = JSONArray.parseArray(list);
            array.add(orderindex);
            valueOperations.set("successPrintCenterList", array);
        }*/
    }

    public int queryOrder(String orderindex){
        Map<String,String> map = new HashMap<>( );
        map.put("deviceNo","kdt2200235");
        map.put("key","72a8b");
        map.put("orderindex",orderindex);
        String resp = HttpClientUtil.sendHttpPost(QUERY_ORDER,map);
        JSONObject jsonObject = JSONObject.parseObject(resp);
        if (jsonObject!=null)
            return (int) jsonObject.get("responseCode");
        return -1;
    }

    public int queryPrinterStatus(){
        Map<String,String> map = new HashMap<>( );
        map.put("deviceNo","kdt2200235");
        map.put("key","72a8b");
        String resp = HttpClientUtil.sendHttpPost(QUERY_PRINTER_STATUS,map);
        JSONObject jsonObject = JSONObject.parseObject(resp);
        if (jsonObject!=null)
            return (int) jsonObject.get("responseCode");
        return -1;
    }
}
