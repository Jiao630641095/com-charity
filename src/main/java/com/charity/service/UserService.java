package com.charity.service;

import com.charity.common.base.service.BaseService;
import com.charity.entity.shiro.User;
import com.github.pagehelper.PageInfo;

/**
 * @Author：王思峰
 * @Description: 用户管理
 * @Date: Created in 14:12 2017/9/21
 * @Modified By:
 */
public interface UserService extends BaseService<User> {

    /**
     * @param pageNum  当前页码
     * @param pageSize  每页显示条数
     * @param username 用户名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @throws Exception
     */
    PageInfo<User> findPage(Integer pageNum ,Integer pageSize ,String username, String startTime, String endTime) throws Exception;

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUserName(String username) throws Exception;

    /**
     * 保存用户信息和关联用户和角色
     * @param user    用户对象
     * @param roleId  角色ID
     */
    Boolean saveUserAndUserRole(User user, Long roleId) throws Exception;

    /**
     * 更新用户信息和关联用户和角色
     * @param user      用户对象
     * @param oldRoleId 旧角色ID
     * @param roleId    角色ID
     * @return
     * @throws Exception
     */
    Boolean updateUserAndUserRole(User user, Long oldRoleId, Long roleId) throws Exception;

    /**
     *
     * @param user
     * @return
     */
    Boolean updatePwd(User user);
}
