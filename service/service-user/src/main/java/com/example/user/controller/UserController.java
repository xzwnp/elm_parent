package com.example.user.controller;


import com.example.entity.R;
import com.example.user.entity.User;
import com.example.user.entity.UserCodeQuery;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-03-15
 */
@RestController
@RequestMapping("/login")
@Api(tags = "用户登录,注册")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/byPassword")
    public R<String> login(@RequestBody User user) {
        String token = userService.login(user.getUsername(), user.getPassword());
        return R.success(token);
    }


    //todo 需要调用短信模块,我实在懒得写了
    @ApiOperation(value = "使用验证码登录")
    @PostMapping("/byCode")
    public R<String> login(@RequestBody UserCodeQuery user) {
        if (!StringUtils.hasLength(user.getPhone())) {
            throw new AuthenticationException("手机号格式不正确!");
        }
        if (!StringUtils.hasLength(user.getCode())) {
            throw new AuthenticationException("未输入验证码!");
        }
        String token = userService.login(user.getPhone(), user.getCode());
        return R.success(token);
    }


}

