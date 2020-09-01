package com.charity.entity.canteen;

import com.charity.common.base.entity.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "s_orderdetail")
public class OrderDetail extends BaseEntity {

    /**
     * 订单ID
     */
    private Long oid;

    /**
     * 商品ID
     */
    private Long cid;

    /**
     * 商品名称
     */
    private String cname;

    /**
     * 商品名称
     */
    private String picture;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 获取订单ID
     *
     * @return oid - 订单ID
     */
    public Long getOid() {
        return oid;
    }

    /**
     * 设置订单ID
     *
     * @param oid 订单ID
     */
    public void setOid(Long oid) {
        this.oid = oid;
    }

    /**
     * 获取商品ID
     *
     * @return cid - 商品ID
     */
    public Long getCid() {
        return cid;
    }

    /**
     * 设置商品ID
     *
     * @param cid 商品ID
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }

    /**
     * 获取商品名称
     *
     * @return cname - 商品名称
     */
    public String getCname() {
        return cname;
    }

    /**
     * 设置商品名称
     *
     * @param cname 商品名称
     */
    public void setCname(String cname) {
        this.cname = cname;
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
     * 获取购买数量
     *
     * @return num - 购买数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置购买数量
     *
     * @param num 购买数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}