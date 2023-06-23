package com.example.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.business.entity.Business;
import com.example.business.service.BusinessService;
import com.example.constant.PageQuery;
import com.example.entity.R;
import com.example.constant.ResultCode;
import com.example.autoconfig.util.PageData;
import icu.ynu.log.annotation.LogRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("admin")
@Api(tags = "商家管理")
@Slf4j
@RequiresRoles("admin")
public class BusinessAdminController {
	@Autowired
	private BusinessService businessService;

	@PostMapping("page")
	@ApiOperation("分页查询商家信息")
	public R<PageData<Business>> page(@ApiIgnore @RequestBody PageQuery params) {
		LambdaQueryWrapper<Business> wrapper = new LambdaQueryWrapper<>();
		if (StringUtils.hasLength(params.getKeyword())) {
			wrapper.eq(Business::getBusinessId, params.getKeyword())
				.or()
				.like(Business::getBusinessName, params.getKeyword())
				.or()
				.like(Business::getBusinessAddress, params.getKeyword());
		}
		Page<Business> businessPage = new Page<>(params.getPage(), params.getPageSize());
		businessService.page(businessPage, wrapper);
		return R.success(new PageData<>(businessPage.getRecords(), businessPage.getTotal()));
	}

	@GetMapping("{id}")
	@ApiOperation("根据id查询商家信息")
	public R<Business> get(@PathVariable("id") Long id) {
		return R.success(businessService.getById(id));
	}

	@PostMapping
	@ApiOperation("保存")
//	@LogRecord(bizId = "#getBizId()", bizType = "商家信息",
//		content = "'新增了商家[' + #business.businessName + ']'")
	public R save(@RequestBody Business business) {
		if (businessService.save(business)) {
			return R.success();
		} else {
			return R.error();
		}

	}

	@PutMapping
	@ApiOperation("修改")
//	@LogRecord(bizId = "#getBizId()", bizType = "商家信息",
//		content = "'更新了商家[' + #business.businessName + ']的信息'")
	public R update(@RequestBody Business business) {

		if (businessService.updateById(business)) {
			return R.success();
		} else {
			return R.error();
		}

	}

	@DeleteMapping
	@ApiOperation("删除")
//	@RequiresPermissions("business:delete")
//	@LogRecord(bizId = "#getBizId()", bizType = "商家信息",
//		content = "'删除了商家[' + #business.businessName + ']'")
	public R delete(@RequestBody List<Long> ids) {
		log.info("待删除:{}", ids);

		return businessService.removeBatchByIds(ids) ? R.success() : R.error(ResultCode.ERROR, "删除失败!");
	}


}