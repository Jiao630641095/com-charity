package com.charity.controller.api.CanteenController;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.PrintCenterUtil;
import com.charity.entity.canteen.Commodity;
import com.charity.entity.canteen.DeliveryInfo;
import com.charity.entity.canteen.Order;
import com.charity.entity.canteen.OrderDetail;
import com.charity.service.CommodityService;
import com.charity.service.DeliveryInfoService;
import com.charity.service.OrderDetailService;
import com.charity.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("canteenApi")
public class CanteenApiController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private DeliveryInfoService deliveryInfoService;

    @RequestMapping("getApiToken")
    public String getApiToken(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object apiToken = valueOperations.get("apiToken");
        if (apiToken==null||StringUtils.isBlank(apiToken.toString())){
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            valueOperations.set("apiToken",uuid,600,TimeUnit.SECONDS);
            apiToken = uuid;
            System.out.println("新的token："+uuid);
        }
        return  apiToken.toString();
    }


    @RequestMapping("/getOrderList")
    @ResponseBody
    public List<Order> getOrderList(String wx){
        if (StringUtils.isBlank(wx)){
            return null;
        }
        Map where = new HashMap();
        where.put("wx",wx);
        PageInfo<Order> pageInfo = orderService.getOrderList(where);
        pageInfo.getList().stream().forEach(order -> {
            List<OrderDetail> orderDetailses = orderDetailService.getOrderDetailsByOrderId(order.getId());
            order.setOrderDetailList(orderDetailses);
        });
        return pageInfo.getList();
    }

    @RequestMapping("/addOrder")
    @ResponseBody
    public Map addOrder(HttpServletRequest request){
        Map res = new HashMap<>();
        String wx = request.getParameter("wx");
        String odList = request.getParameter("orderDetails");
        String description = request.getParameter("description");
        if (StringUtils.isBlank(wx)||StringUtils.isBlank(odList)){
            return null;
        }
        Order order = new Order();
        long timeMillis = System.currentTimeMillis();
        String id = timeMillis + "" + ((int)(Math.random()*900)+100);
        order.setId(new Long(id));
        order.setAmt(new BigDecimal(1.5));
        order.setBoxFee(new BigDecimal(0));
        order.setTransportFee(new BigDecimal(1.5));
        order.setCutleryFee(new BigDecimal(0));
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setWx(wx);
        deliveryInfo.setSort(1);
        List<DeliveryInfo> deliveryInfos = deliveryInfoService.findListByWhere(deliveryInfo);
        if (deliveryInfos==null||deliveryInfos.size()==0){
            res.put("code",4005);
            res.put("msg","请先填写配送地址信息");
            return res;
        }
        deliveryInfo = deliveryInfos.get(0);
        order.setAddress(deliveryInfo.getAddress());
        order.setPhone(deliveryInfo.getPhone());
        order.setUserName(deliveryInfo.getUsername());
        order.setDescription(description);
        order.setStatus(1);
        order.setWx(wx);
        List<OrderDetail> orderDetails = new ArrayList<>();
        JSONObject object = JSONObject.parseObject(odList);
        Set<Map.Entry<String, Object>> entries = object.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        try {
            while (iterator.hasNext()){
                Map.Entry<String, Object> next = iterator.next();
                Commodity commodity = commodityService.findById(new Long(next.getKey()));
                if (commodity!=null){
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setCid(commodity.getId());
                    orderDetail.setCname(commodity.getName());
                    orderDetail.setNum((Integer) next.getValue());
                    orderDetail.setPicture(commodity.getPicture());
                    orderDetail.setPrice(commodity.getPrice());
                    orderDetail.setOid(order.getId());
                    order.setAmt(order.getAmt().add((commodity.getPrice().add(commodity.getBoxFee())).multiply(new BigDecimal(next.getValue().toString()))));
                    order.setBoxFee(order.getBoxFee().add(commodity.getBoxFee().multiply(new BigDecimal(next.getValue().toString()))));
                    orderDetailService.save(orderDetail);
                }
            }
            Integer save = orderService.save(order);
            if (save>0){
                res.put("code",4000);
                res.put("order",order);
                res.put("msg","");
            }else {
                res.put("code",4005);
                res.put("msg","订单保存失败");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOid(order.getId());
            orderDetailService.deleteByWhere(orderDetail);
            res.put("code",4005);
            res.put("msg","订单保存失败");
            return res;
        }
        return res;
    }


    @RequestMapping("/getCommodityList")
    @ResponseBody
    public List<Commodity> getCommodityList(){
        Map where = new HashMap();
        where.put("status",1);
        PageInfo<Commodity> pageInfo = commodityService.getCommodityList(where);
        return pageInfo.getList();
    }

    @RequestMapping("/getOrderDetailsByOrderId")
    public Order getOrderDetailsByOrderId(Long orderId){
        Order order = orderService.findById(orderId);
        if (order==null)
            return null;
        List<OrderDetail> orderDetailses = orderDetailService.getOrderDetailsByOrderId(orderId);
        order.setOrderDetailList(orderDetailses);
        return order;
    }

    @RequestMapping("/getCommodity")
    public Commodity getCommodity(Long id){
        Commodity commodity = commodityService.findById(id);
        return commodity;
    }

   @RequestMapping("/changeOrderState")
    public Map changeOrderState(Order order){
       Map res = new HashMap<>();
       Integer status = order.getStatus();
       order.setStatus(1);
       List<Order> list = orderService.findListByWhere(order);
       if (list==null||list.size()==0){
           res.put("code",4005);
           res.put("msg","支付失败");
           return res;
       }
       order = list.get(0);
       List<OrderDetail> orderDetailses = orderDetailService.getOrderDetailsByOrderId(order.getId());
       order.setOrderDetailList(orderDetailses);
       if (status==2){
            order.setStatus(2);
            try {
                PrintCenterUtil printCenterUtil = new PrintCenterUtil();
                printCenterUtil.addOrder(order);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (status==-1){
           order.setStatus(-1);
       }else {
           res.put("code",4005);
           res.put("msg","订单状态异常");
           return res;
       }
        Integer integer = orderService.updateSelective(order);
        if (integer>0){
            res.put("code",4000);
            res.put("msg","");
        }else {
            res.put("code",4005);
            res.put("msg","签收修改失败");
        }
        return res;
    }


    @RequestMapping("/getDeliveryInfoByWX")
    @ResponseBody
    public DeliveryInfo getDeliveryInfoByWX(String wx){
        DeliveryInfo d = new DeliveryInfo();
        d.setSort(1);
        d.setWx(wx);
        DeliveryInfo deliveryInfo = deliveryInfoService.findOne(d);
        return deliveryInfo;
    }

/*
    @RequestMapping("/getDeliveryInfoListByWX")
    @ResponseBody
    public Map getDeliveryInfoListByWX(String wx){
        Map res = new HashMap<>();
        DeliveryInfo d = new DeliveryInfo();
        d.setWx(wx);
        List<DeliveryInfo> list = deliveryInfoService.findListByWhere(d);
        if (list.size() == 0){
            res.put("list",null);
            res.put("code","4000");
            res.put("msg","暂无预留配送地址信息");
        }else {
            res.put("list",list);
            res.put("code","4000");
        }
        return res;
    }*/


    /**
     * 修改配送信息
     * @param request
     * @return
     */
    @RequestMapping("updateDeliveryInfoByWX")
    public Map updateDeliveryInfoByWX(HttpServletRequest request){
        Map res = new HashMap();
        String openid = request.getParameter("openid");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String id = request.getParameter("id");
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setWx(openid);
        deliveryInfo.setAddress(address);
        deliveryInfo.setPhone(phone);
        deliveryInfo.setUsername(username);
        int i = 0;
        if (StringUtils.isBlank(id)){
            deliveryInfo.setSort(1);
            i = deliveryInfoService.save(deliveryInfo);
        }else {
            deliveryInfo.setId(Long.valueOf(id));
            i = deliveryInfoService.updateSelective(deliveryInfo);
        }
        if (i>0){
            res.put("code",4000);
            res.put("msg","");
        }else {
            res.put("code",4005);
            res.put("msg","配送信息修改失败");
        }
        return  res;
    }

}
