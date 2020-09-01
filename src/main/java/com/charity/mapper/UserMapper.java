package com.charity.mapper;

import com.charity.common.base.mapper.BaseMapper;
import com.charity.entity.shiro.User;
import org.springframework.stereotype.Repository;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 11:53 2017/9/29
 * @Modified By:
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     *
     * @param user
     * @return
     */
    int updatePwd(User user);
}
