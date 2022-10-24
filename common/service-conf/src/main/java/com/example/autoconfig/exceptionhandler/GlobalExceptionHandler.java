package com.example.autoconfig.exceptionhandler;

import com.example.entity.GlobalException;
import com.example.entity.ResultCode;
import com.example.entity.UserInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.entity.R;

/**
 * com.example.commonutils.exceptionhandler
 *
 * @author xzwnp
 * 2022/1/27
 * 14:14
 * Steps：
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //加上这个注解表示用于处理exception异常类
    @ExceptionHandler(Exception.class)
    @ResponseBody //指定返回json数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error(ResultCode.ERROR, "服务器内部错误");
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public R error(GlobalException e) {
        e.printStackTrace();
        String message = e.getMessage() == null ? "服务器内部错误!" : e.getMessage();
        return R.error(ResultCode.ERROR, message);
    }

    @ExceptionHandler(UserInputException.class)
    @ResponseBody
    public R error(UserInputException e) {
        e.printStackTrace();
        String message = e.getMessage() == null ? "请检查用户输入!" : e.getMessage();
        return R.error(ResultCode.INPUT_ERROR, message);
    }
}