package com.charity.entity;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 9:40 2017/10/17
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity {

    private String name;        //项目名称
    private String sketch;      //项目简述
    private String content;     //项目内容
    private String imgSrc;      //项目配图
    private String initiator;   //发起人
    private String isShow;      //是否展示

    private String website;     //所属网站名称
    private String websiteUrl;  //所属网站地址
}
