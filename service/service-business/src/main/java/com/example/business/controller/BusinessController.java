package com.example.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.business.entity.Business;
import com.example.business.entity.Food;
import com.example.business.service.BusinessService;
import com.example.business.service.FoodService;
import com.example.entity.InnerApi;
import com.example.entity.MapBuilder;
import com.example.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaozhiwei
 * @since 2022-05-23
 */
@RestController
@RequestMapping("/")
@CacheConfig(cacheNames = "business")
@Api(tags = "用户端能看到的商家,食物信息")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    FoodService foodService;

    @GetMapping("current/{current}/size/{size}")
//    简易分页缓存
    @Cacheable(key = "#p2 + '-'+ #p0 + '-'+ #p1")
    @ApiOperation(value = "分页查询")
    public List<Business> getBusinessInfo(@PathVariable int current, @PathVariable int size, @RequestParam(required = false) String type) {
        QueryWrapper<Business> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(type != null, "type", type);
        Page<Business> page = new Page<>(current, size);
        businessService.page(page, queryWrapper);
        return page.getRecords();
    }


    @GetMapping("{id}")
    @Cacheable(key = "#id")
    @ApiOperation("获取商家详情,对应的所有食物信息")
    public R<Map<String, Object>> getBusinessById(@PathVariable String id) {
        Business business = businessService.getById(id);
        List<Food> foods = foodService.getFoodListByBusinessId(id);
        Map<String, Object> map = new MapBuilder().put("business", business).put("foods", foods).build();

        return R.success(map);
    }


    /**
     * 这里应该使用推荐算法
     *
     * @param current
     * @param size
     * @param type
     * @return
     */
    @GetMapping("recommend/current/{current}/size/{size}")
    @ApiOperation("todo 获取用户的个性推荐")
    public R getRecommendBusinessInfo(@PathVariable int current, @PathVariable int size, @RequestParam String type) {
        return null;
    }


}
