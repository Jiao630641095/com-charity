package com.charity.entity;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * Created by XWD on 2017/10/19.
 */


public class Policies{

    private Integer policiesid; //政策法规编号

    private String policiestitle;//政策法规标题

    private String policiescontent;//政策法规内容

    private Timestamp policiestime;//政策法规发布时间

    private String state;//是否展示 0不展示 1展示

    private Integer pageNum;
    private Integer pageSize;

    private Integer looknum;//浏览次数

    public Integer getPoliciesid() {
        return policiesid;
    }

    public String getPoliciestitle() {
        return policiestitle;
    }

    public String getPoliciescontent() {
        return policiescontent;
    }

    public Timestamp getPoliciestime() {
        return policiestime;
    }

    public void setPoliciesid(Integer policiesid) {
        this.policiesid = policiesid;
    }
    public void setId(Integer policiesid) {
        this.policiesid = policiesid;
    }
    public void setPoliciestitle(String policiestitle) {
        this.policiestitle = policiestitle;
    }
    public void setTitle(String policiestitle) {
        this.policiestitle = policiestitle;
    }
    public void setPoliciescontent(String policiescontent) {
        this.policiescontent = policiescontent;
    }
    public void setContent(String policiescontent) {
        this.policiescontent = policiescontent;
    }
    public void setPoliciestime(Timestamp policiestime) {
        this.policiestime = policiestime;
    }
    public void setTime(Timestamp policiestime) {
        this.policiestime = policiestime;
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
