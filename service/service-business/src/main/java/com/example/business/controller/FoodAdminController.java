package com.example.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.business.entity.Food;
import com.example.business.entity.query.FoodPageQuery;
import com.example.business.service.FoodService;
import com.example.entity.R;
import com.example.entity.ResultCode;
import com.example.page.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * com.example.food.controller
 *
 * @author xiaozhiwei
 * 2022/12/7
 * 13:10
 */

@RestController
@RequestMapping("/food/admin")
@Api(tags = "食物管理")
@RequiresRoles("admin")
public class FoodAdminController {
    @Autowired
    private FoodService foodService;


    @PostMapping("page")
    @ApiOperation("根据businessId获取")
//    @RequiresPermissions("food:list")
    public R<PageData<Food>> page(@RequestBody FoodPageQuery params) {
        LambdaQueryWrapper<Food> wrapper = new LambdaQueryWrapper<>();
        Page<Food> foodPage = new Page<>(params.getPage(), params.getPageSize());
        wrapper.eq(Food::getBusinessId, params.getBusinessId());
        if (StringUtils.hasLength(params.getKeyword())) {
            wrapper.like(Food::getFoodName, params.getKeyword())
                    .or()
                    .like(Food::getFoodExplain, params.getKeyword());
        }
        foodService.page(foodPage, wrapper);
        return R.success(new PageData<>(foodPage.getRecords(), foodPage.getTotal()));
    }

    @PostMapping
    @ApiOperation("保存")
//    @RequiresPermissions("food:save")
    public R save(@RequestBody Food food) {
        if (foodService.save(food)) {
            return R.success();
        } else {
            return R.error();
        }

    }

    @PutMapping
    @ApiOperation("修改")
//    @RequiresPermissions("food:update")
    public R update(@RequestBody Food food) {

        if (foodService.updateById(food)) {
            return R.success();
        } else {
            return R.error();
        }

    }

    @DeleteMapping
    @ApiOperation("删除")
//    @RequiresPermissions("food:delete")
    public R delete(@RequestBody List<Long> ids) {
        return foodService.removeBatchByIds(ids) ? R.success() : R.error(ResultCode.ERROR, "删除失败!");
    }


}
