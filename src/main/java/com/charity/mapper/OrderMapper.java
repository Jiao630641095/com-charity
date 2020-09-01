package com.charity.mapper;

import com.charity.common.base.mapper.BaseMapper;
import com.charity.entity.canteen.Order;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> getTodayOrderList(Map where);

    Long getTodayOrderListTotal(Map where);

    List<Order> getOrderList(Map where);

    Long getOrderListTotal(Map where);
}