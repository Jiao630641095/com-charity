package com.charity.service;

import com.charity.common.base.service.BaseService;
import com.charity.entity.shiro.Role;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 11:40 2017/9/29
 * @Modified By:
 */
public interface RoleService extends BaseService<Role> {
    /**
     * 根据角色名称查询角色对象信息
     * @param name
     * @return
     */
    Role findByName(String name);
    /**
     * 根据用户ID查询角色对象信息
     * @param userId
     * @return
     */
    Role findByUserId(Long userId);

    /**
     * 根据角色ID删除角色并级联删除该角色和权限的关联信息
     * @param roleId
     * @return
     */
    Boolean deleteRoleAndRolePermissionByRoleId(Long roleId);
}
