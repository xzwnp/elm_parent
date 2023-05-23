package com.example.msmservice.controller;

import com.example.entity.R;
import com.example.util.UserInputException;
import com.example.msmservice.service.MsmService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

/**
 * com.example.msmservice.controller
 *
 * @author xzwnp
 * 2022/3/25
 * 18:10
 * Steps：
 */
@RestController
@RequestMapping("/message/")
@Slf4j
@Api("短信服务")
public class MsmController {
    @Autowired
    MsmService msmService;

    private static final Pattern PHONE_REGEX = Pattern.compile("/^1[3-9]\\d{9}$/\n");

    @GetMapping("getCode/{phone}")
    public R sendMessage(@PathVariable String phone) {
        if (!StringUtils.hasLength(phone) || !PHONE_REGEX.matcher(phone).matches()) {
            throw new UserInputException("手机号格式不正确!");
        }
        String code = msmService.sendMessage(phone);
        log.info(code);
        return R.success(null);
    }


}
