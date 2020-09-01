package com.charity.entity.chanyeyuan;

/**
 * Created by XWD on 2017/12/8.   租房服务
 */
public class TpcRental {

    private Integer id;

    private String floor;//楼层

    private String passageway;//通道

    private Double rent;//单价

    private Double torent;  //模糊查询用的 最大单价

    private Double area;//面积

    private Integer toarea;//模糊查询用的  最大面积

    private String face;//朝向

    private String info; //详细信息

    private String photo1;//封面图片
    private String photo2;//封面图片
    private String photo3;//封面图片
    private String photo4;//封面图片
    private String photo5;//封面图片

    private String roomnum; //门牌号

    private Integer looknum;// 浏览次数

    private String isShow;// 是否审核

    private Integer isUp;// 是否推荐

    private String createTime;    //创建时间

    private String modifyTime;    //修改时间

    private Integer deleteState;  //删除状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getPassageway() {
        return passageway;
    }

    public void setPassageway(String passageway) {
        this.passageway = passageway;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public String getPhoto5() {
        return photo5;
    }

    public void setPhoto5(String photo5) {
        this.photo5 = photo5;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    public Integer getLooknum() {
        return looknum;
    }

    public void setLooknum(Integer looknum) {
        this.looknum = looknum;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }

    public Double getTorent() {
        return torent;
    }

    public void setTorent(Double torent) {
        this.torent = torent;
    }

    public Integer getToarea() {
        return toarea;
    }

    public void setToarea(Integer toarea) {
        this.toarea = toarea;
    }

    public Integer getIsUp() {
        return isUp;
    }

    public void setIsUp(Integer isUp) {
        this.isUp = isUp;
    }
}
