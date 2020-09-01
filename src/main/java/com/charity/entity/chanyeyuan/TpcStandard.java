package com.charity.entity.chanyeyuan;

/**
 * Created by XWD on 2017/12/1.
 * 优惠政策实体类
 */
public class TpcStandard {

    private Integer id;

    private String title;// 标题

    private String content;//内容

    private String author;//发布人

    private Integer looknum; // 浏览次数

    private String isShow;  // 是否审核

    private String createTime;    //创建时间

    private String modifyTime;    //修改时间

    private Integer deleteState;  //删除状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
