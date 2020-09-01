package com.charity.entity;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 12:07 2017/10/12
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Website extends BaseEntity {
    private String name;
    private String url;
}
