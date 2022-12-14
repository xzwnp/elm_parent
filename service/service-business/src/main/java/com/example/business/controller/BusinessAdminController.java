package com.example.business.controller;


import com.alibaba.nacos.client.utils.ValidatorUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.business.entity.Business;
import com.example.business.entity.dto.BusinessDTO;
import com.example.business.service.BusinessService;
import com.example.constant.Constant;
import com.example.constant.PageQuery;
import com.example.entity.R;
import com.example.entity.ResultCode;
import com.example.page.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


/**
 * @author XiaoZhiwei xiao@ynu.icu
 * @since 1.0.0 2022-12-06
 */
@RestController
@RequestMapping("admin")
@Api(tags = "商家管理")
@Slf4j
public class BusinessAdminController {
    @Autowired
    private BusinessService businessService;

    @PostMapping("page")
    @ApiOperation("分页")
//    @RequiresPermissions("business:page")
    public R<PageData<Business>> page(@ApiIgnore @RequestBody PageQuery params) {
        LambdaQueryWrapper<Business> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(params.getInput())) {
            wrapper.eq(Business::getBusinessId, params.getInput())
                    .or()
                    .like(Business::getBusinessName, params.getInput())
                    .or()
                    .like(Business::getBusinessAddress, params.getInput());
        }
        Page<Business> businessPage = new Page<>(params.getPage(), params.getPageSize());
        businessService.page(businessPage, wrapper);

        return R.success(new PageData<>(businessPage.getRecords(), businessPage.getTotal()));
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("business:info")
    public R<Business> get(@PathVariable("id") Long id) {
        return R.success(businessService.getById(id));
    }

    @PostMapping
    @ApiOperation("保存")
    @RequiresPermissions("business:save")
    public R save(@RequestBody Business business) {
        if (businessService.save(business)) {
            return R.success();
        } else {
            return R.error();
        }

    }

    @PutMapping
    @ApiOperation("修改")
    @RequiresPermissions("business:update")
    public R update(@RequestBody Business business) {

        if (businessService.updateById(business)) {
            return R.success();
        } else {
            return R.error();
        }

    }

    @DeleteMapping
    @ApiOperation("删除")
    @RequiresPermissions("business:delete")
    public R delete(@RequestBody List<Long> ids) {
        log.info("待删除:{}", ids);

        return businessService.removeBatchByIds(ids) ? R.success() : R.error(ResultCode.ERROR, "删除失败!");
    }


}