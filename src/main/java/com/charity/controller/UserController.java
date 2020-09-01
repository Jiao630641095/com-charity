package com.charity.controller;

import com.charity.common.annotation.OperationLog;
import com.charity.common.base.controller.BaseController;
import com.charity.common.util.ShiroUtil;
import com.charity.entity.shiro.Role;
import com.charity.entity.shiro.User;
import com.charity.service.RoleService;
import com.charity.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 11:39 2017/9/29
 * @Modified By:
 */
@Controller
@RequestMapping("/admin")
public class UserController extends BaseController {
    private static final String BASE_PATH = "admin/manager/user/";

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    /**
     * 分页查询管理员列表
     *
     * @param pageNum   当前页码
     * @param username  用户名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param modelMap
     * @return
     */
    @RequiresPermissions("user:list")
    @GetMapping(value = "/user")
    public String list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            String username, String startTime, String endTime, ModelMap modelMap) throws Exception {
        try {
            log.debug("分页查询管理员列表参数! pageNum = {}, username = {}, username = {}, startTime = {}, endTime = {}", pageNum, username, startTime, endTime);
            PageInfo<User> pageInfo = userService.findPage(pageNum, PAGESIZE, username, startTime, endTime);
            log.info("分页查询管理员列表结果！ pageInfo = {}", pageInfo);
            modelMap.put("pageInfo", pageInfo);
            modelMap.put("username", username);
            modelMap.put("startTime", startTime);
            modelMap.put("endTime", endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BASE_PATH + "list";
    }

    /**
     * 根据主键ID删除管理员
     *
     * @param id
     * @return
     */
    @OperationLog(value = "删除管理员")
    @RequiresPermissions("user:delete")
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        log.debug("删除管理员! id = {}", id);

        User user = userService.findById(Long.valueOf(id));
        if (null == user) {
            log.info("删除管理员不存在! id = {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("管理员不存在!");
        }

        if(user.getIsAdmin()){
            log.info("禁用|启用管理员失败, 超级管理员不允许操作! id = {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("超级管理员不允许操作!");
        }

            userService.deleteById(Long.valueOf(id));
        log.info("删除管理员成功! id = {}", id);
        return ResponseEntity.ok("已删除!");
    }

    /**
     * 批量删除管理员
     * @param ids
     * @return
     */
    @OperationLog(value = "批量删除管理员")
    @DeleteMapping(value = "/user/batch/{ids}")
    public ResponseEntity<Void> deleteBatch(@PathVariable("ids") List<Object> ids) {
        log.debug("批量删除管理员! ids = {}", ids);

        if (null == ids) {
            log.info("批量删除管理员不存在! ids = {}", ids);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userService.deleteByCondition(User.class, "id", ids);
        log.info("批量删除管理员成功! ids = {}", ids);

        return ResponseEntity.ok(null);
    }

    /**
     * 禁用|启用
     * @param id
     * @return
     */
    @OperationLog(value = "禁用|启用管理员")
    @RequiresPermissions("user:status")
    @PutMapping(value = "/user/status/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable("id") Long id){
        log.debug("禁用|启用管理员参数! id = {}", id);
        User user = userService.findById(id);

        if (null == user) {
            log.info("管理员不存在! id = {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("管理员不存在!");
        }

        if(user.getIsAdmin()){
            log.info("禁用|启用管理员失败, 超级管理员不允许操作! id = {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("超级管理员不允许操作!");
        }

        //禁用启用
        user.setIsLock(!user.getIsLock());
        userService.updateSelective(user);

        log.info("禁用|启用管理员成功! id = {}", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 跳转到管理员添加页面
     * @return
     */
    @RequiresPermissions("user:create")
    @GetMapping(value = "/user/add")
    public String add(ModelMap modelMap){
        List<Role> roleList = roleService.findAll();
        modelMap.put("roleList", roleList);
        log.info("跳转到管理员添加页面!");
        return BASE_PATH + "add";
    }

    /**
     * 添加管理员
     */
    @OperationLog(value = "添加管理员")
    @RequiresPermissions("user:create")
    @ResponseBody
    @PostMapping(value = "/user")
    public ModelMap saveUser(User user) throws Exception {
        ModelMap messagesMap = new ModelMap();

        log.debug("添加管理员参数! user = {}", user);          //user.getRoleName()数值为角色ID
        user.setSalt(FAILURE);//加盐
        Boolean flag = userService.saveUserAndUserRole(user, Long.valueOf(user.getRoleName()));
        if(flag){
            log.info("添加管理员成功! userId = {}", user.getId());
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","添加成功!");
            return messagesMap;
        }
        log.info("添加管理员失败, 但没有抛出异常! userId = {}", user.getId());
        messagesMap.put("status",FAILURE);
        messagesMap.put("message","添加失败!");
        return messagesMap;
    }

    /**
     * 跳转到管理员编辑页面
     * @return
     */
    @RequiresPermissions("user:update")
    @GetMapping(value = "/user/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap modelMap){
        User user = userService.findById(Long.valueOf(id));
        List<Role> roleList = roleService.findAll();
        Role role = roleService.findByUserId(user.getId());

        log.info("跳转到管理员编辑页面！id = {}", id);
        modelMap.put("model", user);
        modelMap.put("roleList", roleList);
        modelMap.put("role", role);
        return BASE_PATH + "edit";
    }

    /**
     * 跳转到个人资料页面
     * @return
     */
//    @RequiresPermissions("user:update")
    @GetMapping(value = "/user/userinfo")
    public String userinfo( ModelMap modelMap){
        //获得当前登陆用户
        User sessionuser = ShiroUtil.getUserEntity();
        User user = userService.findById(Long.valueOf(sessionuser.getId()));
        List<Role> roleList = roleService.findAll();
        Role role = roleService.findByUserId(user.getId());

        log.info("跳转到个人资料页面！id = {}", sessionuser.getId());
        modelMap.put("model", user);
        modelMap.put("roleList", roleList);
        modelMap.put("role", role);
        return BASE_PATH + "userInfo";
    }




    /**
     * 更新管理员信息
     * @param id         用户ID
     * @param user       用户信息
     * @param oldRoleId  旧角色ID
     * @param roleId     角色ID
     * @return
     */
    @OperationLog(value = "编辑管理员")
    @RequiresPermissions("user:update")
    @ResponseBody
    @PutMapping(value = "/user/{id}")
    public ModelMap updateUser(@PathVariable("id") Long id, User user, Long oldRoleId, Long roleId) throws Exception {
        ModelMap messagesMap = new ModelMap();
        log.debug("编辑管理员参数! id= {}, user = {}", id, user);

        User u = userService.findById(id);
        if (null == u) {
            log.info("编辑管理员不存在! id = {}", id);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","管理员不存在!");
            return messagesMap;
        }

        if(u.getIsAdmin()){
            log.info("编辑管理员失败, 超级管理员不允许操作! id = {}", id);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","超级管理员不允许操作!");
            return messagesMap;
        }

        Boolean flag = userService.updateUserAndUserRole(user, oldRoleId, roleId);
        if(flag){
            log.info("编辑管理员成功! id= {}, user = {}", id, user);
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","编辑成功!");
            return messagesMap;
        }
        log.info("编辑管理员失败,但没有抛出异常 ! id= {}, user = {}", id, user);
        messagesMap.put("status",FAILURE);
        messagesMap.put("message","编辑失败!");
        return messagesMap;
    }

    /**
     * 检验用户名是否存在
     * @param username
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/user/isExist")
    public Boolean isExist(Long id, String username) throws Exception {
        boolean flag = true;
        log.debug("检验用户名是否存在参数! id= {}, username= {}", id, username);
        User record = userService.findByUserName(username);
        if (null != record && !record.getId().equals(id)) {
            flag = false;
        }
        log.info("检验用户名是否存在结果! flag = {}", flag);
        return flag;
    }
}
