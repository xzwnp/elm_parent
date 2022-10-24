package com.example.order.entity.query;

import com.example.order.entity.vo.FoodVo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * com.example.order.entity.query
 *
 * @author xzwnp
 * 2022/6/8
 * 17:26
 */
@Data
public class OrderQuery {
    private String businessName;
    private String businessId;
    private BigDecimal totalPrice;
    private FoodVo[] foodList;
}
