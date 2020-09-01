package com.charity.service;

import com.charity.common.base.service.BaseService;
import com.charity.entity.canteen.OrderDetail;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface OrderDetailService extends BaseService<OrderDetail> {

    List<OrderDetail> getOrderDetailsByOrderId(Long orderId);
}
