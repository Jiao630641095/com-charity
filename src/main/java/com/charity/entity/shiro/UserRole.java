package com.charity.entity.shiro;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author：王思峰
 * @Description: 用户角色中间表
 * @Date: Created in 11:30 2017/9/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity {
    private Long userId;
    private Long roleId;
}
