package com.charity.service;

import com.charity.common.base.service.BaseService;
import com.charity.entity.canteen.Commodity;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface CommodityService extends BaseService<Commodity> {

    PageInfo<Commodity> getCommodityList(Map where);



}
