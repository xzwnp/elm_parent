package com.example.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.business.entity.Food;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaozhiwei
 * @since 2022-04-12
 */
public interface FoodService extends IService<Food> {
    List<Food> getFoodListByBusinessId(String id);
}
