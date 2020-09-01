package com.charity.service.impl;

import com.charity.common.base.service.impl.BaseServiceImpl;
import com.charity.entity.canteen.Order;
import com.charity.mapper.OrderMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService extends BaseServiceImpl<Order> implements com.charity.service.OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageInfo<Order> getTodayOrderList(Map where) {

        try{
            List<Order> list =  orderMapper.getTodayOrderList(where);
            Long num =  orderMapper.getTodayOrderListTotal(where);
            PageInfo<Order> page = new PageInfo<Order>(list);
            page.setTotal(num);
            return page;
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
    }

    @Override
    public PageInfo<Order> getOrderList(Map where) {
        try{
            List<Order> list =  orderMapper.getOrderList(where);
            Long num =  orderMapper.getOrderListTotal(where);
            PageInfo<Order> page = new PageInfo<Order>(list);
            page.setTotal(num);
            return page;
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
    }
}
