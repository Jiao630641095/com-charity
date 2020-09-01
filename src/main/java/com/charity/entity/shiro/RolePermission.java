package com.charity.entity.shiro;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author：王思峰
 * @Description: 角色权限中间表
 * @Date: Created in 11:31 2017/9/29
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePermission extends BaseEntity {
    private Long roleId;
    private Long permissionId;
}
