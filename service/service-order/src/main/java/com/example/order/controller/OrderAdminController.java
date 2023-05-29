package com.example.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import com.example.constant.PageQuery;
import com.example.entity.R;
import com.example.autoconfig.util.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


/**
 * @author XiaoZhiwei xiao@ynu.icu
 * @since 1.0.0 2022-12-06
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "订单管理")
@RequiresRoles(value = {"admin", "super_admin"}, logical = Logical.OR)
public class OrderAdminController {
    @Autowired
    private OrderService orderService;

    @PostMapping("page")
    @ApiOperation("分页")
//    @RequiresPermissions("order:page")
    public R<PageData<Order>> page(@ApiIgnore @RequestBody PageQuery params) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Order::getBusinessName, params.getKeyword())
                .or()
                .like(Order::getUserId, params.getKeyword());
        Page<Order> orderPage = new Page<>(params.getPage(), params.getPageSize());
        orderService.page(orderPage, wrapper);

        return R.success(new PageData<>(orderPage.getRecords(), orderPage.getTotal()));
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
//    @RequiresPermissions("order:info")
    public R<Order> get(@PathVariable("id") Long id) {
        return R.success(orderService.getById(id));
    }

    @PostMapping
    @ApiOperation("保存")
//    @RequiresPermissions("order:save")
    public R save(@RequestBody Order order) {
        if (orderService.save(order)) {
            return R.success();
        } else {
            return R.error();
        }

    }

    @PutMapping
    @ApiOperation("修改")
//    @RequiresPermissions("order:update")
    public R update(@RequestBody Order order) {

        if (orderService.updateById(order)) {
            return R.success();
        } else {
            return R.error();
        }

    }

    @DeleteMapping
    @ApiOperation("删除")
//    @RequiresPermissions("order:delete")
    public R delete(@RequestBody List<Long> ids) {

        orderService.removeBatchByIds(ids);

        return R.success();
    }


}