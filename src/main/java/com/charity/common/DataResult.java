package com.charity.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

/**
 * @Author：王思峰
 * @Description: 通用返回结果
 * @Date: Created in 11:49 2017/10/9
 * @Modified By:
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
public class DataResult<T> {

    /**
     * 操作执行情况代码，0-表示成功，其他表示失败
     */
    private final int code;

    /**
     * 执行情况描述
     */
    private final String msg;

    /**
     * 返回结果
     */
    private final T result;

    public DataResult(int code, String msg){
        this(code, msg, null);
    }

    public DataResult(int code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}
