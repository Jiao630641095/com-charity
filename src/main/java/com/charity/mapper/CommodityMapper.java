package com.charity.mapper;

import com.charity.common.base.mapper.BaseMapper;
import com.charity.entity.canteen.Commodity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface CommodityMapper extends BaseMapper<Commodity> {
    List<Commodity> getCommodityList(Map where);

    Long getCommodityListTotal(Map where);
}