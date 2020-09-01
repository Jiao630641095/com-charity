package com.charity.entity.chanyeyuan;

/**
 * Created by XWD on 2018/5/15.  今日菜单
 */
public class TpcTodayFood {

    private Integer id;

    private String name;//名称

    private Double price;//单价

    private String photo;//图片

    private String unit;//单位

    private Integer status;//是否展示

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
