package com.charity.service.impl;

import com.charity.common.base.service.impl.BaseServiceImpl;
import com.charity.entity.shiro.Role;
import com.charity.entity.shiro.RolePermission;
import com.charity.mapper.RoleMapper;
import com.charity.mapper.RolePermissionMapper;
import com.charity.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 11:46 2017/9/29
 * @Modified By:
 */
@Transactional
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;


    @Transactional(readOnly = true)
    @Override
    public Role findByName(String name) {
        Role role = new Role();
        role.setName(name);
        return this.findOne(role);
    }

    @Override
    public Role findByUserId(Long userId) {
        return roleMapper.findByUserId(userId);
    }

    @Override
    public Boolean deleteRoleAndRolePermissionByRoleId(Long roleId) {
        //删除角色
        int count1 = this.deleteById(roleId);

        //级联删除该角色所关联的权限
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermissionMapper.delete(rolePermission);
        return count1 == 1;
    }
}