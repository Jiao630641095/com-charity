package com.charity.entity.log;

import com.charity.common.base.entity.BaseEntity;
import com.xiaoleilu.hutool.util.StrUtil;
import groovy.transform.EqualsAndHashCode;
import lombok.Data;

import javax.persistence.Table;
import java.util.Map;

/**
 * @Author：王思峰
 * @Description: 系统日志
 * @Date: Created in 15:23 2017/9/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_log")
public class Log extends BaseEntity {

    /**
     * 应用名称
     */
    private String appName;
    /**
     * 日志类型 0操作日志，1异常日志
     */
    private Integer logType;
    /**
     * 操作人
     */
    private String username;
    /**
     * 用户操作
     */
    private String operation;
    /**
     * 请求方法名称(全路径)
     */
    private String methodName;
    /**
     * 请求方式(GET,POST,DELETE,PUT)
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * 访问者ip
     */
    private String requestIp;
    /**
     * 请求uri
     */
    private String requestUri;
    /**
     * 异常编码
     */
    private String exceptionCode;
    /**
     * 异常详情
     */
    private String exceptionDetail;
    /**
     * 耗时(单位：毫秒)
     */
    private Long timeConsuming;
    /**
     * 客户端信息
     */
    private String userAgent;

    public void setParams(Map<String, String[]> paramMap) {
        if (paramMap == null) {
            return;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
            params.append("".equals(params.toString()) ? "" : "&").append(param.getKey()).append("=");
            String paramValue = (param.getValue() != null
                    && param.getValue().length > 0 ? param.getValue()[0] : "");
            params.append(StrUtil.subPre(StrUtil.endWithIgnoreCase(
                    param.getKey(), "password") ? "" : paramValue, 100));
        }
        this.requestParams = params.toString();
    }
}
