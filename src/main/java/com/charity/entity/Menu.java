package com.charity.entity;

import com.charity.common.base.entity.BaseEntity;
import groovy.transform.EqualsAndHashCode;
import lombok.Data;

import javax.persistence.Table;

/**
 * @Author：王思峰
 * @Description: 左侧导航栏
 * @Date: Created in 12:06 2017/9/27
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_menu")
public class Menu extends BaseEntity implements java.io.Serializable {
    private String title;
    private String href;
    private Boolean spread; //是否展开
    private String target;
    private String icon;
//    private List<Menu> children;
}
