package com.charity.common.annotation;

import java.lang.annotation.*;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 15:40 2017/9/28
 * @Modified By:
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    /**
     * 日志操作描述
     * @return
     */
    String value()  default "";
}
