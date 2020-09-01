package com.charity.entity.chanyeyuan;

import java.sql.Timestamp;

/**
 * Created by XWD on 2018/3/7.
 */
public class CompanyJob {

    private Integer id;
    private Integer uid; //用户id
    private Integer cid; //公司id
    private String cname; //公司名称
    private String name; //职位名称
    private Integer size; //招聘人数
    private Integer education;  //学历
    private Integer minSalary = 0;  //最低薪水
    private Integer maxSalary; //最高薪水
    private Integer hideSalary; //面议
    private Integer age;  //工作年限
    private String info; //工作简介
    private Timestamp create_time;

    private Integer isUp;  //是否推荐
    private Integer isShow; //是否展示

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Integer getHideSalary() {
        return hideSalary;
    }

    public void setHideSalary(Integer hideSalary) {
        this.hideSalary = hideSalary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Integer getIsUp() {
        return isUp;
    }

    public void setIsUp(Integer isUp) {
        this.isUp = isUp;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
