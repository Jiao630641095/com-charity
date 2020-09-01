package com.charity.service;

import com.charity.common.base.service.BaseService;
import com.charity.entity.adai.Qrcode;
import com.charity.entity.canteen.Commodity;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface QrcodeService extends BaseService<Qrcode> {


    boolean addQrcode(Qrcode qrcode);

    List<Qrcode> list();

    PageInfo<Qrcode> findPage(Map where);

    boolean changeStutus(Qrcode qrcode);

    Qrcode findQrcodeForShow(Integer id);
}
