package com.example.oss.controller;

import com.example.entity.R;
import com.example.constant.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * com.example.oss.controller
 *
 * @author xiaozhiwei
 * 2022/12/7
 * 15:39
 */
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public R<?> maxUploadSizeHandler() {
        return R.error(ResultCode.ERROR, "图片过大!不能超过2MB");
    }
}
