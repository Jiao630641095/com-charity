package com.charity.mapper;

import com.charity.common.base.mapper.BaseMapper;
import com.charity.entity.shiro.Role;
import org.springframework.stereotype.Repository;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 11:47 2017/9/29
 * @Modified By:
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户ID查询角色对象信息
     * @param userId
     * @return
     */
    Role findByUserId(Long userId);
}
