package com.charity.service;

import com.charity.common.base.service.BaseService;
import com.charity.entity.log.Log;
import com.github.pagehelper.PageInfo;

/**
 * @Author：王思峰
 * @Description: 日志管理
 * @Date: Created in 15:34 2017/9/28
 * @Modified By:
 */
public interface LogService extends BaseService<Log> {

    /**
     * 分页查询日志列表
     * @param pageNum
     * @param pageSize
     * @param username
     * @param startTime
     * @param endTime
     * @return
     */
    PageInfo<Log> findPage(Integer pageNum, Integer pageSize, String username, String startTime, String endTime);
}
