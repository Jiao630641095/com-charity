package com.charity.mapper;

import com.charity.common.base.mapper.BaseMapper;
import com.charity.entity.TreeNode;
import com.charity.entity.shiro.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 11:56 2017/9/29
 * @Modified By:
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据用户ID查询该用户所拥有的权限列表
     */
    List<Permission> findListPermissionByUserId(Long userId);

    /**
     * 根据用户ID查询用户菜单列表
     */
    List<Permission> findMenuListByUserId(Long userId);

    /**
     * 返回树列表
     * @return
     */
    List<TreeNode> findTreeList();


    List<TreeNode> findTreeListByPid(Long pid);
}
