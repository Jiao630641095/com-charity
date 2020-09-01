package com.charity.controller;

import com.charity.common.annotation.OperationLog;
import com.charity.common.base.controller.BaseController;
import com.charity.common.validator.Create;
import com.charity.common.validator.Update;
import com.charity.entity.TreeNode;
import com.charity.entity.shiro.Role;
import com.charity.entity.shiro.UserRole;
import com.charity.service.PermissionService;
import com.charity.service.RolePermissionService;
import com.charity.service.RoleService;
import com.charity.service.UserRoleService;
import com.github.pagehelper.PageInfo;
import com.xiaoleilu.hutool.json.JSONUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：王思峰
 * @Description: 角色Controller
 * @Date: Created in 12:01 2017/9/29
 * @Modified By:
 */
@Validated  //开启方法级别验证支持
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

    private static final String BASE_PATH = "admin/manager/role/";

    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RolePermissionService rolePermissionService;
    @Resource
    private UserRoleService userRoleService;

    /**
     * 分页查询角色列表
     *
     * @param pageNum  当前页码
     * @param modelMap
     * @return
     */
  //  @RequiresPermissions("role:list")
    @GetMapping
    public String list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       ModelMap modelMap) {
        log.debug("分页查询角色列表参数! pageNum = {}", pageNum);
        PageInfo<Role> pageInfo = roleService.findPageListByWhere(pageNum, PAGESIZE, null);
        log.info("分页查询角色列表结果！ pageInfo = {}", pageInfo);
        modelMap.put("pageInfo", pageInfo);
        return BASE_PATH + "list";
    }

    /**
     * 根据主键ID删除角色
     *
     * @param id
     * @return
     */
    @OperationLog(value = "删除角色")
    @RequiresPermissions("role:delete")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        log.debug("删除管理员! id = {}", id);

        if (null == id) {
            log.info("删除角色不存在! id = {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("删除角色不存在!");
        }

        //判断该角色下是否绑定了用户 若有，则不允许删除
        UserRole userRole = userRoleService.findByUserIdAndRoleId(null, id);
        if (null != userRole) {
            log.info("删除角色失败，该角色下有用户存在，不允许删除! id = {}", id);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Boolean flag = roleService.deleteRoleAndRolePermissionByRoleId(id);
        if (flag) {
            log.info("删除角色成功! id = {}", id);
            return ResponseEntity.ok("删除成功!");
        }
        log.info("删除角色失败，但没有抛出异常! id = {}", id);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 跳转到角色添加页面
     *
     * @return
     */
    @RequiresPermissions("role:create")
    @GetMapping(value = "/add")
    public String add(ModelMap modelMap) {
        //所有权限列表
        List<TreeNode> treeNodeList = permissionService.findTreeList();
        modelMap.put("treeNodeList", treeNodeList);
        return BASE_PATH + "add";
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @OperationLog(value = "添加角色")
    @RequiresPermissions("role:create")
    @ResponseBody
    @PostMapping
    public ModelMap saveRole(Role role, String ids) {
        ModelMap messagesMap = new ModelMap();

        log.debug("添加角色参数! role = {}", role);
        //执行保存
        roleService.save(role);
        log.info("添加角色成功! roleId = {}", role.getId());
        if(!ids.isEmpty()){
            //授权
            String[] str = ids.split(",");
            Long[] permissionIds = new Long[str.length];
            for (int i = 0; i < str.length; i++) {
                permissionIds[i] = Long.valueOf(str[i]);
            }
            rolePermissionService.saveOrUpdate(role.getId(), permissionIds);
            log.info("角色授权成功! roleId = {}", role.getId());
        }
        messagesMap.put("status", SUCCESS);
        messagesMap.put("message", "添加成功!");
        return messagesMap;
    }

    /**
     * 跳转到角色编辑页面
     *
     * @return
     */
    @RequiresPermissions("role:update")
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap modelMap) throws Exception {
        log.debug("跳转到编辑角色信息页面参数! id = {}", id);
        Role role = roleService.findById(Long.valueOf(id));
        //所有权限列表
        List<TreeNode> treeNodeList = permissionService.findTreeList();
        //当前角色所拥有的权限ID集合
        String permissionIds = rolePermissionService.findPermissionIdsByRoleId(Long.valueOf(id));
        log.info("成功!, id = {}", id);

        log.info("跳转到编辑角色信息页面成功!, id = {}", id);
        modelMap.put("model", role);
        modelMap.put("permissionIds", permissionIds);
        modelMap.put("treeNodeList", treeNodeList);
        return BASE_PATH + "edit";
    }

    /**
     * 更新角色信息
     * @param id
     * @param role @Validated({Update.class})
     * @return
     */
    @OperationLog(value = "编辑角色")
    @RequiresPermissions("role:update")
    @ResponseBody
    @PutMapping(value = "/{roleId}")
    public ModelMap updateRole(@PathVariable("roleId") String roleId, Role role ,String ids) {
        ModelMap messagesMap = new ModelMap();

        log.debug("编辑角色参数! roleId= {}, role = {}", roleId, role);

        role.setId(Long.valueOf(roleId));
        roleService.updateSelective(role);
        log.info("编辑角色成功! roleId= {}, role = {}", roleId, role);
        //授权
        if(!ids.isEmpty()){
            String[] str = ids.split(",");
            Long[] permissionIds = new Long[str.length];
            for (int i = 0; i < str.length; i++) {
                permissionIds[i] = Long.valueOf(str[i]);
            }
            rolePermissionService.saveOrUpdate(role.getId(), permissionIds);
        }
        log.info("角色授权成功! roleId = {}", role.getId());
        messagesMap.put("status", SUCCESS);
        messagesMap.put("message", "编辑成功!");
        return messagesMap;
    }
    /**
     * 检验角色名是否存在
     * @param id
     * @param name
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/isExist")
    public Boolean isExist(Long id, String name) {
        boolean flag = true;
        log.debug("检验角色名是否存在参数! id= {}, name= {}", id, name);
        Role record = roleService.findByName(name);
        if (null != record && !record.getId().equals(id)) {
            flag = false;
        }
        log.info("检验角色名是否存在结果! flag = {}", flag);
        return flag;
    }
//    /**
//     * 跳转到角色授权页面
//     *
//     * @param roleId
//     * @return
//     */
//    @RequiresPermissions("role:permission")
//    @GetMapping("/permission/{roleId}")
//    public String toRolePermission(@PathVariable("roleId") String roleId, ModelMap modelMap) throws Exception {
//
//        //所有权限列表
//        List<TreeNode> treeNodeList = permissionService.findTreeList();
//        String jsonStr = JSONUtil.toJsonStr(treeNodeList);
//
//        //当前角色所拥有的权限ID集合
//        String permissionIds = rolePermissionService.findPermissionIdsByRoleId(Long.valueOf(roleId));
//
//        modelMap.put("roleId", roleId);
//        modelMap.put("treeNodeList", jsonStr);
//        modelMap.put("permissionIds", permissionIds);
//        return BASE_PATH + "role-permission";
//    }
//
//    /**
//     * 执行角色授权
//     *
//     * @param roleId
//     * @param permissionIds
//     * @return
//     * @throws Exception
//     */
//    @OperationLog(value = "角色授权")
//    @RequiresPermissions("role:permission")
//    @ResponseBody
//    @PutMapping("/{roleId}/permission")
//    public ModelMap rolePermission(@PathVariable("roleId") Long roleId, @Size(message = "请至少选择一个", min = 1) Long[] permissionIds) throws Exception {
//        ModelMap messagesMap = new ModelMap();
//
//        rolePermissionService.saveOrUpdate(roleId, permissionIds);
//        messagesMap.put("status", SUCCESS);
//        messagesMap.put("message", "授权成功!");
//        return messagesMap;
//    }


}
