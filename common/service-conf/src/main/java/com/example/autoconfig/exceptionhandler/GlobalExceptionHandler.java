package com.example.autoconfig.exceptionhandler;

import com.example.entity.GlobalException;
import com.example.constant.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.entity.R;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * com.example.commonutils.exceptionhandler
 *
 * @author xzwnp
 * 2022/1/27
 * 14:14
 * Steps：
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //登录失败,token无效
    @ExceptionHandler(AuthenticationException.class)
    public R unauthorizedExceptionHandler(AuthenticationException e) {
        log.error("登录失败或token无效,错误信息:{}", e.getMessage());
        return R.error(ResultCode.TOKEN_ERROR, "token过期或无效");
    }

    //权限验证错误
    @ExceptionHandler(AuthorizationException.class)
    public R unauthorizedExceptionHandler(AuthorizationException e) {
        log.error("{}", e.getMessage());
        return R.error(ResultCode.NO_PERMISSION, "没有相关权限!");
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public R missingRequestHeaderExceptionHandler(MissingRequestHeaderException e) {
        //token不存在的情况单独处理
        if ("userId".equals(e.getHeaderName())) {
            return R.error(ResultCode.TOKEN_ERROR, "token不存在!");
        }
        log.error("{}", e.getMessage());
        return R.error(ResultCode.ERROR, e.getMessage());
    }

    //对应路径不存在
    @ExceptionHandler(NoHandlerFoundException.class)
    public R noHandlerFoundExceptionHandler(NoHandlerFoundException e) {

        log.error("请求路径错误{}", e.getMessage());
        return R.error(ResultCode.PATH_NOT_EXIST, "请求路径错误");
    }

    @ExceptionHandler(GlobalException.class)
    public R error(GlobalException e) {
        e.printStackTrace();
        String message = e.getMessage() == null ? "服务器内部错误!" : e.getMessage();
        return R.error(ResultCode.ERROR, message);
    }

    //加上这个注解表示用于处理exception异常类
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        e.printStackTrace();
        return R.error(ResultCode.ERROR, "服务器内部错误");
    }


}