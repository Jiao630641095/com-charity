package com.charity.service.impl;

import com.charity.common.base.service.impl.BaseServiceImpl;
import com.charity.entity.shiro.UserRole;
import com.charity.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 12:10 2017/9/29
 * @Modified By:
 */
@Transactional
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {
    @Override
    public UserRole findByUserIdAndRoleId(Long userId, Long roleId) {
        return null;
    }
}
