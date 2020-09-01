package com.charity.entity.chanyeyuan;

/**
 * Created by XWD on 2018/6/12. 本月到期房屋统计
 */
public class ExpireRental {

    private Integer id;

    private String qyname;//楼层

    private String roomnum; //门牌号

    private String name;//联系人

    private String phone;//手机

    private Integer paytype;//付款方式

    private String email;//邮箱

    private Integer lastday;//剩余天数

    private String pay_date;//付款时间


    public String getQyname() {
        return qyname;
    }

    public void setQyname(String qyname) {
        this.qyname = qyname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLastday() {
        return lastday;
    }

    public void setLastday(Integer lastday) {
        this.lastday = lastday;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
