package com.example.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.entity.Role;
import com.example.admin.service.RoleService;
import com.example.constant.PageQuery;
import com.example.entity.R;
import com.example.entity.ResultCode;
import com.example.page.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/role")
@Api(tags = "角色管理")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("page")
    @ApiOperation("分页")
//    @RequiresPermissions("role:page")
    public R<PageData<Role>> page(@ApiIgnore @RequestBody PageQuery params) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(params.getKeyword())) {
            wrapper.eq(Role::getId, params.getKeyword())
                    .or()
                    .like(Role::getRole, params.getKeyword());
        }
        Page<Role> rolePage = new Page<>(params.getPage(), params.getPageSize());
        roleService.page(rolePage, wrapper);

        return R.success(new PageData<>(rolePage.getRecords(), rolePage.getTotal()));
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("role:info")
    public R<Role> get(@PathVariable("id") Long id) {
        return R.success(roleService.getById(id));
    }

    @PostMapping
    @ApiOperation("保存")
    @RequiresPermissions("role:save")
    public R save(@RequestBody Role role) {
        if (roleService.save(role)) {
            return R.success();
        } else {
            return R.error();
        }

    }

    @PutMapping
    @ApiOperation("修改")
    @RequiresPermissions("role:update")
    public R update(@RequestBody Role role) {

        if (roleService.updateById(role)) {
            return R.success();
        } else {
            return R.error();
        }

    }

    @DeleteMapping
    @ApiOperation("删除")
    @RequiresPermissions("role:delete")
    public R delete(@RequestBody List<Long> ids) {
        log.info("待删除:{}", ids);

        return roleService.removeBatchByIds(ids) ? R.success() : R.error(ResultCode.ERROR, "删除失败!");
    }


}