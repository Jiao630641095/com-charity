package com.charity.entity.canteen;

import com.charity.common.base.entity.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "s_delivery_info")
public class DeliveryInfo extends BaseEntity {

    private String wx;

    private String username;

    private String address;

    private String phone;

    private Integer sort;

    /**
     * @return wx
     */
    public String getWx() {
        return wx;
    }

    /**
     * @param wx
     */
    public void setWx(String wx) {
        this.wx = wx;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}