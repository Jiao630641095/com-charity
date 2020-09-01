package com.charity.entity.canteen;

import com.charity.common.base.entity.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "s_order")
public class Order extends BaseEntity {

    /**
     * 配送地址
     */
    private String address;

    /**
     * 金额
     */
    private BigDecimal amt;


    /**
     * 餐盒费
     */
    @Column(name = "box_fee")
    private BigDecimal boxFee;


    /**
     * 餐具费
     */
    @Column(name = "cutlery_fee")
    private BigDecimal cutleryFee;


    /**
     * 配送费
     */
    @Column(name = "transport_fee")
    private BigDecimal transportFee;

    /**
     * 订餐人姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 订餐人电话
     */
    private String phone;

    /**
     * 备注
     */
    private String description;

    /**
     * 备注
     */
    private String evaluate;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 微信openId
     */
    private String wx;

    /**
     * 订单详情
     * @return
     */
    @Transient
    private List<OrderDetail> orderDetailList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取金额
     *
     * @return amt - 金额
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 设置金额
     *
     * @param amt 金额
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * 获取订餐人姓名
     *
     * @return user_name - 订餐人姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置订餐人姓名
     *
     * @param userName 订餐人姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取订餐人电话
     *
     * @return phone - 订餐人电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置订餐人电话
     *
     * @param phone 订餐人电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取备注
     *
     * @return description - 备注
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置备注
     *
     * @param description 备注
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取订单状态
     *
     * @return status - 订单状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态
     *
     * @param status 订单状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取微信openId
     *
     * @return wx - 微信openId
     */
    public String getWx() {
        return wx;
    }

    /**
     * 设置微信openId
     *
     * @param wx 微信openId
     */
    public void setWx(String wx) {
        this.wx = wx;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public BigDecimal getBoxFee() {
        return boxFee;
    }

    public void setBoxFee(BigDecimal boxFee) {
        this.boxFee = boxFee;
    }

    public BigDecimal getCutleryFee() {
        return cutleryFee;
    }

    public void setCutleryFee(BigDecimal cutleryFee) {
        this.cutleryFee = cutleryFee;
    }

    public BigDecimal getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(BigDecimal transportFee) {
        this.transportFee = transportFee;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }
}