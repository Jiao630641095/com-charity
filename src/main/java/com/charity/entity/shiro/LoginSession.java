package com.charity.entity.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author：王思峰
 * @Description: 登用户信息
 * @Date: Created in 11:47 2017/10/9
 * @Modified By:
 */
@Data
public class LoginSession implements Serializable {

    private static final long serialVersionUID = 1847417165637588522L;
    /**
     * 登录用户sessionId
     */
    private Serializable sessionId;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录IP
     */
    private String loginIP;

}