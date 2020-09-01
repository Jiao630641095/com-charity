package com.charity.entity.chanyeyuan;

/**
 * Created by XWD on 2017/11/28.   产业园 首页 新闻 实体类
 */
public class TpcNews {

    private Integer id;

    private String title;// 新闻标题

    private String img;         // 新闻封面

    private String abstracted;//新闻摘要

    private String content;//新闻内容

    private String author;//新闻发布人

    private Integer newCounts; // 浏览次数

    private String isShow;  // 是否审核

    private String createTime;    //创建时间

    private String modifyTime;    //修改时间

    private Integer deleteState;  //删除状态

    private Integer tid;       //类型id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracted() {
        return abstracted;
    }

    public void setAbstracted(String abstracted) {
        this.abstracted = abstracted;
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

    public Integer getNewCounts() {
        return newCounts;
    }

    public void setNewCounts(Integer newCounts) {
        this.newCounts = newCounts;
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

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}
