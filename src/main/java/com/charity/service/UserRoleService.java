package com.charity.service;

import com.charity.common.base.service.BaseService;
import com.charity.entity.shiro.UserRole;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 12:02 2017/9/29
 * @Modified By:
 */
public interface UserRoleService extends BaseService<UserRole> {
    /**
     * 根据用户ID和角色ID查询用户和角色绑定信息
     * @param userId
     * @param roleId
     * @return
     */
    UserRole findByUserIdAndRoleId(Long userId, Long roleId);
}
