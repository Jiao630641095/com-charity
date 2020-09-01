package com.charity.service;

import com.charity.common.base.service.BaseService;
import com.charity.entity.canteen.Order;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface OrderService extends BaseService<Order> {

    PageInfo<Order> getTodayOrderList(Map where);

    PageInfo<Order> getOrderList(Map where);
}
