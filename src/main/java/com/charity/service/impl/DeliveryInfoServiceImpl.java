package com.charity.service.impl;

import com.charity.common.base.service.BaseService;
import com.charity.common.base.service.impl.BaseServiceImpl;
import com.charity.entity.canteen.DeliveryInfo;
import com.charity.mapper.DeliveryInfoMapper;
import com.charity.service.DeliveryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryInfoServiceImpl extends BaseServiceImpl<DeliveryInfo> implements DeliveryInfoService {

    @Autowired
    private DeliveryInfoMapper deliveryInfoMapper;

    @Override
    public Integer update(DeliveryInfo record) {
        return super.update(record);
    }
}
