package com.example.order.service.client;

import com.example.entity.R;
import com.example.entity.dto.FoodDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * com.example.order.service.client
 *
 * @author xzwnp
 * 2022/6/8
 * 16:47
 */
@FeignClient(value = "service-business")
public interface BusinessClient {
    @GetMapping("business/food/inner/ids")
    R<List<FoodDto>> queryFoodByFoodIds(@RequestParam List<Integer> foodIds);
}
