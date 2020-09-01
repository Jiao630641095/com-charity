package com.charity.entity;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author：王思峰
 * @Description: 项目事件
 * @Date: Created in 15:08 2017/10/17
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Event extends BaseEntity {
    private Integer pid;        //项目ID
    private String name;        //事件上级名称
    private String title;       //事件标题
    private String isShow;      //是否展示
    private String illustration;//配图
    private String contents;    //事件内容

    private String websiteUrl;  //所属网站地址

}
