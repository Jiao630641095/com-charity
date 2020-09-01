package com.charity.common.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author：王思峰
 * @Description: 统一异常处理
 * @Date: Created in 10:28 2017/11/3
 * @Modified By:
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(Exception e) throws Exception {
        e.printStackTrace();
        if(e  instanceof UnauthorizedException){
            return "/admin/403";
        }
        return "/admin/500";
    }
}
