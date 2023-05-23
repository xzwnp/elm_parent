package com.example.business.controller;

import com.example.business.entity.Food;
import com.example.business.service.FoodService;
import com.example.entity.dto.FoodDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * com.example.business.controller
 *
 * @author xzwnp
 * 2022/12/27
 * 14:59
 */
@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    FoodService foodService;

    @GetMapping("/inner/ids")
    public List<FoodDto> getFoodByFoodIds(@RequestParam List<Integer> foodIds) {
        List<Food> foods = foodService.listByIds(foodIds);
        List<FoodDto> foodDtos = foods.stream().map((food) -> {
            FoodDto dto = new FoodDto();
            BeanUtils.copyProperties(food, dto);
            return dto;
        }).collect(Collectors.toList());
        return foodDtos;
    }
}
