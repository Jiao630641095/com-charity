package com.charity.entity;

/**
 * Created by ZhangXu on 2017/9/5.
 */
public class UploadResult {
    Integer code;
    String msg;
    UploadResultData data = new UploadResultData();

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UploadResultData getData() {
        return data;
    }

    public void setData(UploadResultData data) {
        this.data = data;
    }
}
