package com.charity.service.impl;

import com.charity.common.base.service.impl.BaseServiceImpl;
import com.charity.entity.shiro.Role;
import com.charity.entity.shiro.User;
import com.charity.entity.shiro.UserRole;

import com.charity.mapper.RoleMapper;
import com.charity.mapper.UserMapper;
import com.charity.mapper.UserRoleMapper;
import com.charity.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 14:13 2017/9/21
 * @Modified By:
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Transactional(readOnly=true)
    @Override
    public PageInfo<User> findPage(Integer pageNum , Integer pageSize , String username, String startTime, String endTime) throws Exception {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(username)){
            criteria.andLike("username", "%"+username+"%");
        }if(StrUtil.isNotEmpty(startTime)){
            criteria.andGreaterThanOrEqualTo("createTime", DateUtil.beginOfDay(DateUtil.parse(startTime)));
        }if(StrUtil.isNotEmpty(endTime)){
            criteria.andLessThanOrEqualTo("createTime", DateUtil.endOfDay(DateUtil.parse(endTime)));
        }

        //倒序
        example.orderBy("createTime").desc();

        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = this.selectByExample(example);

        for (User user : userList) {
            Role role = roleMapper.findByUserId(user.getId());
            if (null != role){
                user.setRoleName(role.getName());
            }
        }
        return new PageInfo<User>(userList);
    }

    @Transactional(readOnly=true)
    @Override
    public User findByUserName(String username) {
        User user = new User();
        user.setUsername(username);
        return this.findOne(user);
    }

    @Override
    public Boolean saveUserAndUserRole(User user, Long roleId) throws Exception{
        int count = 0;
        //加密
//        user.setPassword(SecureUtil.md5().digestHex(user.getPassword()));
        user.setPassword(user.getPassword());
        user.setIsLock(false);
        user.setIsDelete(false);
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if(Role.ROLE_TYPE.equalsIgnoreCase(role.getPerms())){
            user.setIsAdmin(true);
        }else {
            user.setIsAdmin(false);
        }
        count = this.save(user);

        //关联用户和角色信息
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(user.getId());
        userRole.setCreateTime(user.getCreateTime());
        userRole.setModifyTime(user.getCreateTime());
        count = userRoleMapper.insert(userRole);

        return count == 1;
    }

    @CacheEvict(value = "goodAuthenticationCache", key = "#user.username")
    @Override
    public Boolean updateUserAndUserRole(User user, Long oldRoleId, Long roleId) throws Exception {
        int count = 0;
        //加密
      //  user.setPassword(SecureUtil.md5().digestHex(user.getPassword()));
        user.setPassword(user.getPassword());
        if(!oldRoleId.equals(roleId)){
            Role role = roleMapper.selectByPrimaryKey(roleId);
            if(Role.ROLE_TYPE.equalsIgnoreCase(role.getPerms())){
                user.setIsAdmin(true);
            }else {
                user.setIsAdmin(false);
            }
        }
        count = this.updateSelective(user);

        //更新用户角色信息
        if(!oldRoleId.equals(roleId)){
            UserRole userRole = new UserRole();
            userRole.setRoleId(oldRoleId);
            userRole.setUserId(user.getId());
            UserRole ur = userRoleMapper.selectOne(userRole);
            ur.setRoleId(roleId);
            ur.setModifyTime(user.getModifyTime());
            count = userRoleMapper.updateByPrimaryKeySelective(ur);
        }
        return count == 1;
    }

    @Override
    public Boolean updatePwd(User user) {
        int i =userMapper.updatePwd(user);
        return i>0?true:false;
    }
}
