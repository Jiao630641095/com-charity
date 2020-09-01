package com.charity.service;

import com.charity.common.base.service.BaseService;
import com.charity.entity.shiro.RolePermission;

import java.util.List;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 12:02 2017/9/29
 * @Modified By:
 */
public interface RolePermissionService  extends BaseService<RolePermission> {
    /**
     * 根据角色ID获取当前角色下所有权限ID集合
     * @param roleId    角色ID
     * @return
     * @throws Exception
     */
    List<Long> findListPermissionIdsByRoleId(Long roleId) throws Exception;
    /**
     * 根据角色ID获取当前角色下所有权限ID集合，以,分割
     * @param roleId    角色ID
     * @return
     * @throws Exception
     */
    String findPermissionIdsByRoleId(Long roleId) throws Exception;

    /**
     * 角色授权
     * @param roleId 角色ID
     * @param permissionIds 权限id集合
     */
    void saveOrUpdate(Long roleId, Long[] permissionIds);
}
