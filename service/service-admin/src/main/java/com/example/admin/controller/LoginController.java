package com.example.admin.controller;

import com.example.admin.entity.dto.AdminInfoDto;
import com.example.admin.entity.form.UserLoginForm;
import com.example.admin.service.UserService;
import com.example.entity.R;
import com.example.util.JwtEntity;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.MethodNotAllowedException;


/**
 * com.example.demo.controller
 *
 * @author xiaozhiwei
 * 2022/11/1
 * 19:38
 */
@RestController
@RequestMapping("/")
@Api(tags = "管理系统登录管理")
public class LoginController {
    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public R<String> login(@RequestBody UserLoginForm form) {
        System.out.println("收到登录请求");
        //获取当前user
        String token = userService.login(form.getName(), form.getPassword());
        return R.success(token);
    }

    @PostMapping("/register")
    public R<String> register() throws NoSuchMethodException {
        throw new NoSuchMethodException("后台管理系统注册?想屁吃哦");
    }

    @GetMapping("info")
    @RequiresAuthentication
    public R<AdminInfoDto> info() {
        JwtEntity user = (JwtEntity) SecurityUtils.getSubject().getPrincipal();
        return R.success(new AdminInfoDto(user.getUsername(), user.getNickname(), user.getRoles()));
    }

    @DeleteMapping("out")
    public R<?> logout() {
        System.out.println("退出登录");
        return R.success();
    }

}
