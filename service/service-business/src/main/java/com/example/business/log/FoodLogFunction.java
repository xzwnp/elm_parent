package com.example.business.log;

import com.example.business.entity.Food;
import com.example.business.service.FoodService;
import com.example.util.ComparbleUtil;
import icu.ynu.log.annotation.LogRecordFunction;
import icu.ynu.log.annotation.LogRecordFunctionBean;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * com.example.serviceedu.log
 *
 * @author xiaozhiwei
 * 2023/3/22
 * 9:13
 */
@LogRecordFunctionBean
public class FoodLogFunction {
	static FoodService foodService;

	public FoodLogFunction(FoodService foodService) {
		FoodLogFunction.foodService = foodService;
	}

	@LogRecordFunction("getUpdatedFoodInfo")
	public static String getCourseName(Food food) {
		List<String> res = new ArrayList<>();
		Food oldFood = foodService.getById(food.getFoodId());
		if (oldFood == null) {
			return "更新失败:该菜品并不存在!";
		}
		//比较两个对象的字段差异
		List<ComparbleUtil.ComparbleResult> updatedFields = ComparbleUtil.compareInstance(oldFood, food);
		for (ComparbleUtil.ComparbleResult field : updatedFields) {
			StringBuilder sb = new StringBuilder()
				.append(getTrueFieldName(Food.class, field.getFieldName()))
				.append(":")
				.append(field.getFieldContent())
				.append("->")
				.append(field.getNewFieldContent());

			res.add(sb.toString());
		}
		return res.toString();

	}

	/**
	 * 根据ApiModelProperty注解,获取某个field的中文名
	 */
	static String getTrueFieldName(Class<?> clazz, String fieldName) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
			if (annotation == null) {
				return fieldName;
			}
			return annotation.value();
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}
}
