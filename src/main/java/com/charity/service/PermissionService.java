package com.charity.service;

import com.charity.common.base.service.BaseService;
import com.charity.entity.TreeNode;
import com.charity.entity.shiro.Permission;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 12:02 2017/9/29
 * @Modified By:
 */
public interface PermissionService extends BaseService<Permission>{
    /**
     * 分页查询权限列表
     *
     * @param pageNum  当前页面
     * @param pageSize 每页显示条数
     * @param name     权限名称
     * @return
     */
    PageInfo<Permission> findPage(Integer pageNum, Integer pageSize, String name);

    /**
     * 根据用户ID查询该用户所拥有的权限
     *
     * @param UserId
     * @return
     */
    List<Permission> findListPermissionByUserId(Long userId);

    /**
     * 根据用户ID查询用户菜单列表
     */
    List<Permission> findMenuListByUserId(Long userId);

    /**
     * 根据资源类型查询权限列表
     * @param type
     * @return
     */
    List<Permission> findListByType(String type);

    /**
     * 返回树列表
     * @return
     */
    List<TreeNode> findTreeList();

    /**
     * 根据权限ID删除权限并级联删除权限和角色的关联信息
     * @param permissionId
     * @return
     */
    Boolean deletePermissionAndRolePermissionByPermissionId(Long permissionId);

    /**
     * 根据资源名称模糊匹配
     * @param menuName
     * @return
     */
    List<Permission> findListByMenuName(String menuName);
}
