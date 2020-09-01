package com.charity.common.base.mapper;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
/**
 * @Author：王思峰
 * @Description: 继承自己的MyMapper 特别注意，该接口不能被扫描到，否则会出错
 * @Date: Created in 16:07 2017/9/28
 * @Modified By:
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T> {
}
