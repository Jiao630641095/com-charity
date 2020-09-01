package com.charity.entity.shiro;

import com.charity.common.base.entity.BaseEntity;
import com.charity.common.validator.All;
import com.charity.common.validator.Create;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @Author：王思峰
 * @Description: 角色表
 * @Date: Created in 11:32 2017/9/29
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    //超级管理员标识
    public static final String ROLE_TYPE = "ROLE_ADMIN";

    /**
     * 角色名
     */
    @NotNull(message = "角色不能为空!", groups = {All.class})
    private String name;

    /**
     * 角色标识
     */
    @NotNull(message = "角色标识不能为空!", groups = {All.class})
    private String perms;

    /**
     * 备注
     */
    @NotNull(message = "备注不能为空!", groups = {Create.class})
    private String remark;
}
