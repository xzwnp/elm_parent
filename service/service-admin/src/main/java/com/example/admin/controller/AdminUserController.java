package com.example.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.entity.User;
import com.example.admin.service.UserService;
import com.example.constant.PageQuery;
import com.example.entity.R;
import com.example.constant.ResultCode;
import com.example.autoconfig.util.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


/**
 * @author XiaoZhiwei xiao@ynu.icu
 * @since 1.0.0 2022-12-06
 */
@RestController
@RequestMapping("/adminUser")
@Api(tags = "管理管理员用户")
@Slf4j
public class AdminUserController {
    @Autowired
    private UserService userService;

    @PostMapping("page")
    @ApiOperation("分页")
    @RequiresRoles("super_admin")
    public R<PageData<User>> page(@ApiIgnore @RequestBody PageQuery params) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(params.getKeyword())) {
            wrapper.eq(User::getId, params.getKeyword())
                    .or()
                    .like(User::getUsername, params.getKeyword());
        }
        Page<User> userPage = new Page<>(params.getPage(), params.getPageSize());
        userService.page(userPage, wrapper);
        List<User> userList = userPage.getRecords();
        //过滤掉用户密码
        for (User user : userList) {
            user.setPassword(null);
        }
        return R.success(new PageData<>(userList, userPage.getTotal()));
    }

    @PostMapping
    @ApiOperation("保存")
    @RequiresRoles("super_admin")
    public R save(@RequestBody User user) {
        if (userService.save(user)) {
            return R.success();
        } else {
            return R.error();
        }

    }

    @PutMapping
    @ApiOperation("修改")
    @RequiresRoles("super_admin")
    public R update(@RequestBody User user) {

        if (userService.updateById(user)) {
            return R.success();
        } else {
            return R.error();
        }

    }

    @DeleteMapping
    @ApiOperation("删除")
    @RequiresRoles("super_admin")
    public R delete(@RequestBody List<Long> ids) {
        log.info("待删除:{}", ids);

        return userService.removeBatchByIds(ids) ? R.success() : R.error(ResultCode.ERROR, "删除失败!");
    }


}