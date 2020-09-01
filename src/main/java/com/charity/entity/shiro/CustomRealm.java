package com.charity.entity.shiro;

import com.charity.common.util.EhCacheUtil;
import com.charity.mapper.PermissionMapper;
import com.charity.mapper.RoleMapper;
import com.charity.mapper.UserMapper;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：王思峰
 * @Description: 自定义Realm
 * @Date: Created in 13:40 2017/9/21
 */
public class CustomRealm extends AuthorizingRealm {
    protected final static Log log = LogFactory.get(CustomRealm.class);

    @Resource
    private UserMapper userMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RoleMapper roleMapper;
    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("##################执行Shiro权限认证##################");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User record = null;
        try {
            record = userMapper.findByUserName(token.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(record == null){
            throw new UnknownAccountException();// 没找到帐号
        }
        if (Boolean.TRUE.equals(record.getIsLock())) {
            throw new LockedAccountException(); // 帐号锁定
        }

        Role role = roleMapper.findByUserId(record.getId());

        //设置角色名称
        record.setRoleName(role.getName());

        //将此用户存放到登录认证info中，无需自己做密 码对比，Shiro使用CredentialsMatcher会为我们进行密码对比校验
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                record, record.getPassword(), getName());
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                record, // 用户名
//                record.getPassword(), // 密码
//                ByteSource.Util.bytes(record.getSalt()),// salt=username+salt
//                getName() // realm name
//        );
        return authenticationInfo;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        log.info("=========执行授权===========");
        //获取主身份信息
        User user = (User) principals.getPrimaryPrincipal();

        List<Permission> permissionList = permissionMapper.findListPermissionByUserId(user.getId());

        //单独定一个集合对象
        List<String> permissions = new ArrayList<String>();
        if(permissionList!=null){
            for(Permission permission:permissionList){
                //将数据库中的权限标签 符放入集合
                permissions.add(permission.getPerms());
            }
        }

        //查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }
    /**
     * 清除当前用户认证缓存信息
     */
    public void clearCachedAuthenticationInfo() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCachedAuthenticationInfo(principals);
    }

    /**
     * 清除当前用户授权缓存信息
     */
    public void clearCachedAuthorizationInfo() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除当前用户认证和授权缓存信息
     */
    public void clearCache() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

    /**
     * 清除所有用户授权缓存信息
     */
    public void clearCachedAuthorizationInfoAll(){
        String cacheName = super.getAuthorizationCacheName();
        EhCacheUtil.removeAll(cacheName);
    }
}
