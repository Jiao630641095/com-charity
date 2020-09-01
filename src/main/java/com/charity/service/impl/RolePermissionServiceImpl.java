package com.charity.service.impl;

import com.charity.common.base.service.impl.BaseServiceImpl;
import com.charity.entity.shiro.CustomRealm;
import com.charity.entity.shiro.RolePermission;
import com.charity.mapper.RolePermissionMapper;
import com.charity.service.RolePermissionService;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 12:06 2017/9/29
 * @Modified By:
 */
@Service
@Transactional
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements RolePermissionService {
    @Resource
    private CustomRealm customRealm;
    @Resource
    RolePermissionMapper rolePermissionMapper;


    @Transactional(readOnly = true)
    @Override
    public List<Long> findListPermissionIdsByRoleId(Long roleId) throws Exception {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        List<RolePermission> rolePermissionList = this.findListByWhere(rolePermission);
        List<Long> permissionIds = new ArrayList<Long>();
        for (RolePermission permission : rolePermissionList) {
            permissionIds.add(permission.getPermissionId());
        }
        return permissionIds;
    }

    @Transactional(readOnly = true)
    @Override
    public String findPermissionIdsByRoleId(Long roleId) throws Exception {
        List<Long> permissionIds = this.findListPermissionIdsByRoleId(roleId);
//        StringUtils.join(permissionIds, ",");
        return  StrUtil.join(",", permissionIds);
    }

    @CacheEvict(value = "menuListCache", allEntries = true)
    @Override
    public void saveOrUpdate(Long roleId, Long[] permissionIds) {
        //先删除当前角色所拥有的权限再重现插入
        RolePermission delRolePermission = new RolePermission();
        delRolePermission.setRoleId(roleId);
        super.deleteByWhere(delRolePermission);

        List<RolePermission> rolePermissionList = this.getRolePermissionList(roleId, permissionIds);
        //循环授权
        if(rolePermissionList.size() > 0){
            for (RolePermission rp : rolePermissionList) {
                this.rolePermissionMapper.insert(rp);
            }
        }

        //清除所有用户授权缓存信息，使其重新加载
        //customRealm.clearCachedAuthorizationInfoAll();
    }


    /**
     * 封装角色和权限的关系并返回
     * @param roleId
     * @param permissionIds
     * @return
     */
    private List<RolePermission> getRolePermissionList(Long roleId, Long[] permissionIds){
        List<RolePermission> rolePermissionList = new ArrayList<RolePermission>();
        RolePermission rolePermission = null;
        if(permissionIds == null){
            return Collections.emptyList();
        }
        for (Long permissionId : permissionIds) {
            rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(roleId);
            rolePermission.setCreateTime(new Date());
            rolePermission.setModifyTime(rolePermission.getCreateTime());
            rolePermissionList.add(rolePermission);
        }
        return rolePermissionList;
    }
}
