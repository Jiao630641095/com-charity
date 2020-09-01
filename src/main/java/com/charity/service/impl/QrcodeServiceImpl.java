package com.charity.service.impl;

import com.charity.common.base.service.impl.BaseServiceImpl;
import com.charity.entity.adai.Qrcode;
import com.charity.entity.canteen.DeliveryInfo;
import com.charity.mapper.DeliveryInfoMapper;
import com.charity.mapper.QrcodeMapper;
import com.charity.service.DeliveryInfoService;
import com.charity.service.QrcodeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QrcodeServiceImpl extends BaseServiceImpl<Qrcode> implements QrcodeService {

    @Autowired
    private QrcodeMapper qrcodeMapper;

    @Override
    public boolean addQrcode(Qrcode qrcode) {
        if (qrcode.getId()!=null){
            Qrcode q = qrcodeMapper.selectByPrimaryKey(qrcode.getId());
            if (qrcode.getUserId().equals(q.getUserId())){
                return qrcodeMapper.updateByPrimaryKey(qrcode)>0;
            }else {
                return false;
            }
        }
        return qrcodeMapper.insert(qrcode)>0;
    }

    @Override
    public List<Qrcode> list() {
        return qrcodeMapper.selectAll();
    }

    @Override
    public PageInfo<Qrcode> findPage(Map where) {
        try{
            List<Qrcode> list =  qrcodeMapper.getQrcodeList(where);
            Long num =  qrcodeMapper.getQrcodeListTotal(where);
            PageInfo<Qrcode> page = new PageInfo<Qrcode>(list);
            page.setTotal(num);
            return page;
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
    }

    @Override
    public boolean changeStutus(Qrcode qrcode) {
        Qrcode qrcode1 = qrcodeMapper.selectByPrimaryKey(qrcode.getId());
        qrcode1.setStatus(qrcode.getStatus());
        Qrcode qrcodeForShow = qrcodeMapper.findQrcodeForShow();
        if (qrcodeForShow!=null){
            if (qrcodeForShow.getId() != qrcode.getId()){
                qrcodeForShow.setStatus(0);
                qrcodeMapper.updateByPrimaryKey(qrcodeForShow);
            }
        }
        return qrcodeMapper.updateByPrimaryKey(qrcode1)>0;
    }

    @Override
    public Qrcode findQrcodeForShow(Integer id) {
        return qrcodeMapper.findQrcodeById(id);
    }
}
