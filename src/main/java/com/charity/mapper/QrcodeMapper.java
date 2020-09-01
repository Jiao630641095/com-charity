package com.charity.mapper;

import com.charity.common.base.mapper.BaseMapper;
import com.charity.entity.adai.Qrcode;
import com.charity.entity.canteen.DeliveryInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface QrcodeMapper extends BaseMapper<Qrcode> {
    List<Qrcode> getQrcodeList(Map where);

    Long getQrcodeListTotal(Map where);

    Qrcode findQrcodeForShow();

    Qrcode findQrcodeById(Integer id);
}