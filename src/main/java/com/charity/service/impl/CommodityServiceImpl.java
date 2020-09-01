package com.charity.service.impl;

import com.charity.common.base.service.impl.BaseServiceImpl;
import com.charity.entity.canteen.Commodity;
import com.charity.entity.canteen.Order;
import com.charity.mapper.CommodityMapper;
import com.charity.service.CommodityService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommodityServiceImpl   extends BaseServiceImpl<Commodity> implements CommodityService {

        @Autowired
        private CommodityMapper commodityMapper;

    @Override
    public PageInfo<Commodity> getCommodityList(Map where) {
        try{
            List<Commodity> list =  commodityMapper.getCommodityList(where);
            Long num =  commodityMapper.getCommodityListTotal(where);
            PageInfo<Commodity> page = new PageInfo<Commodity>(list);
            page.setTotal(num);
            return page;
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
    }
}
