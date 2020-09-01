package com.charity.entity;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 12:29 2017/10/12
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class News extends BaseEntity {
    private String title;       //标题
    private String digest;      //内容摘要
    private String content;     //内容
    private String newsAuthor;  //发布人
    private String newsTime;    //创建日期
    private Integer newCounts;  //浏览次数
    private String isShow;      //是否展示
    private String imgUrl;      //新闻配图
    private String href;        //新闻路径
    private Integer tid;        //新闻类型
    private Integer isHard;     //重要新闻展示
    private Integer pageNum;
    private Integer pageSize;

    private String website;     //所属网站名称
    private String websiteUrl;  //所属网站地址

    private List<Event> events; //项目事件
}
