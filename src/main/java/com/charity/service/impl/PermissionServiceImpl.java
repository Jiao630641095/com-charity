package com.charity.service.impl;

import com.charity.common.base.service.impl.BaseServiceImpl;
import com.charity.entity.TreeNode;
import com.charity.entity.shiro.Permission;
import com.charity.entity.shiro.RolePermission;
import com.charity.mapper.PermissionMapper;
import com.charity.mapper.RolePermissionMapper;
import com.charity.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 12:11 2017/9/29
 * @Modified By:
 */
@Transactional
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Transactional(readOnly=true)
    @Override
    public PageInfo<Permission> findPage(Integer pageNum, Integer pageSize, String name) {
        Example example = new Example(Permission.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(name)){
            criteria.andLike("name", "%"+name+"%");
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Permission> PermissionList = this.selectByExample(example);
        return new PageInfo<Permission>(PermissionList);
    }


    @Transactional(readOnly=true)
    @Override
    public List<Permission> findListPermissionByUserId(Long userId) {
        return permissionMapper.findListPermissionByUserId(userId);
    }

    /**
     * 根据用户ID获取菜单列表    优先从缓存中获取数据
     */
    @Cacheable(value = "menuListCache", key = "#userId")
    @Transactional(readOnly=true)
    @Override
    public List<Permission> findMenuListByUserId(Long userId) {
        return permissionMapper.findMenuListByUserId(userId);
    }

    @Transactional(readOnly=true)
    @Override
    public List<Permission> findListByType(String type) {
        Permission permission = new Permission();
        permission.setType(type);
        return this.findListByWhere(permission);
    }

    /**
     * 左侧菜单
     */
    @Transactional(readOnly=true)
    @Override
    public List<TreeNode> findTreeList() {
        List<TreeNode> list = permissionMapper.findTreeList();
        for (TreeNode n : list) {
            List<TreeNode> children = this.permissionMapper.findTreeListByPid(n.getId());
            n.setChildren(children);
            for (TreeNode s : children) {
                List<TreeNode> childrens = this.permissionMapper.findTreeListByPid(s.getId());
                s.setChildren(childrens);
            }
        }
        return list;
    }

    @Override
    public Boolean deletePermissionAndRolePermissionByPermissionId(Long permissionId) {
        //删除权限
        int count1 = this.deleteById(permissionId);

        //删除该权限和角色的关联信息
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermissionId(permissionId);
        rolePermissionMapper.delete(rolePermission);
        return count1 == 1;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Permission> findListByMenuName(String menuName) {
        Example example = new Example(Permission.class);
        Example.Criteria criteria = example.createCriteria();

        if(StrUtil.isNotEmpty(menuName)){
            criteria.andLike("name", "%"+menuName+"%");
        }
        return this.selectByExample(example);
    }
}
