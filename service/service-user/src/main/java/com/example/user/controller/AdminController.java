package com.example.user.controller;

import java.util.List;

import com.example.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.user.entity.Admin;
import com.example.user.service.AdminService;


@Api(tags = "对象功能接口")
@RestController
@RequestMapping("/admin")
public class AdminController{
    @Autowired
    private AdminService adminService;
    
    /** 
     *
     * @return 实例对象
     */
    @ApiOperation("登录")
    @GetMapping("{login}")
    public R login(Admin admin){
        //懒得封装query了
        return R.success(adminService.login(admin));
    }

}