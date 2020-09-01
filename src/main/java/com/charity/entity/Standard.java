package com.charity.entity;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * Created by XWD on 2017/10/19.
 *
 */
public class Standard  {

    private Integer standardid;//行动规范 编号

    private String standardtitle;//行动规范 标题

    private String standardcontent;//行动规范 内容

    private Timestamp standardtime;//行动规范 发布时间

    private String state;//是否展示 “”不展示 1展示


    private Integer pageNum;
    private Integer pageSize;

    private Integer looknum;//浏览次数

    public Integer getStandardid() {
        return standardid;
    }

    public void setStandardid(Integer standardid) {
        this.standardid = standardid;
    }

    public void setId(Integer standardid) {
        this.standardid = standardid;
    }
    public String getStandardtitle() {
        return standardtitle;
    }

    public void setStandardtitle(String standardtitle) {
        this.standardtitle = standardtitle;
    }

    public void setTitle(String standardtitle) {
        this.standardtitle = standardtitle;
    }

    public String getStandardcontent() {
        return standardcontent;
    }

    public void setStandardcontent(String standardcontent) {
        this.standardcontent = standardcontent;
    }

    public void setContent(String standardcontent) {
        this.standardcontent = standardcontent;
    }
    public Timestamp getStandardtime() {
        return standardtime;
    }

    public void setStandardtime(Timestamp standardtime) {
        this.standardtime = standardtime;
    }

    public void setTime(Timestamp standardtime) {
        this.standardtime = standardtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getLooknum() {
        return looknum;
    }

    public void setLooknum(Integer looknum) {
        this.looknum = looknum;
    }
}
