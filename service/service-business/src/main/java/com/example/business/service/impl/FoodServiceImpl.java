package com.example.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.business.entity.Food;
import com.example.business.mapper.FoodMapper;
import com.example.business.service.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaozhiwei
 * @since 2022-04-12
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {

    @Override
    public List<Food> getFoodListByBusinessId(String id) {
        QueryWrapper<Food> wrapper = new QueryWrapper<Food>();
        wrapper.eq("businessId",id);
        return baseMapper.selectList(wrapper);
    }
}
