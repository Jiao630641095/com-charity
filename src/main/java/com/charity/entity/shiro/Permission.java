package com.charity.entity.shiro;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author：王思峰
 * @Description: 权限
 * @Date: Created in 11:56 2017/9/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity {
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源类型：0,1,2(目录,菜单,按钮)
     */
    private String type;
    /**
     * 访问url地址
     */
    private String url;
    /**
     * 权限代码,多个用逗号分隔
     * menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     */
    private String perms;
    /**
     * 父节点id,一级节点为0
     */
    private Long parentId;
    /**
     * 父节点名称
     */
    private String parentName;
    /**
     * 父节点id列表串，用/分割
     */
    private String parentIds;
    /**
     * 排序号
     */
    private Long sort;
    /**
     * 是否禁用  true禁用  false 启用
     */
    private Boolean isLock;

    /**
     * 菜单图标
     */
    private String icon;
}
