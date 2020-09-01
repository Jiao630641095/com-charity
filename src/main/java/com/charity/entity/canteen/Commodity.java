package com.charity.entity.canteen;

import com.charity.common.base.entity.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "s_commodity")
public class Commodity extends BaseEntity {


    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品名称
     */
    private String picture;

    /**
     * 价格
     */
    private BigDecimal price;


    /**
     * 餐具费
     */
    @Column(name = "box_fee")
    private BigDecimal boxFee;

    /**
     * 备注
     */
    private String description;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 展示状态
     */
    private Integer status;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 获取商品名称
     *
     * @return name - 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取商品名称
     *
     * @return picture - 商品名称
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置商品名称
     *
     * @param picture 商品名称
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getBoxFee() {
        return boxFee;
    }

    public void setBoxFee(BigDecimal boxFee) {
        this.boxFee = boxFee;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}