package com.charity.mapper;

import com.charity.common.base.mapper.BaseMapper;
import com.charity.entity.canteen.OrderDetail;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
    List<OrderDetail> getOrderDetailsByOrderId(Long orderId);
}