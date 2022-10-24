package com.example.user.controller;


import com.example.entity.R;
import com.example.entity.UserInputException;
import com.example.user.entity.User;
import com.example.user.entity.UserCodeQuery;
import com.example.user.service.UserService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/user/login")
public class UserController {
    @Autowired
    private UserService memberService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/byPassword")
    public R<String> login(@RequestBody User user) {
        String token = memberService.login(user);
        return R.success(token);
    }

    @ApiOperation(value = "使用验证码登录")
    @PostMapping("/byCode")
    public R<String> login(@RequestBody UserCodeQuery user) {
        if (!StringUtils.hasLength(user.getPhone())){
            throw new UserInputException("手机号格式不正确!");
        }
        if (!StringUtils.hasLength(user.getCode())){
            throw new UserInputException("未输入验证码!");
        }
        String token = memberService.login(user.getPhone(),user.getCode());
        return R.success(token);
    }


}
