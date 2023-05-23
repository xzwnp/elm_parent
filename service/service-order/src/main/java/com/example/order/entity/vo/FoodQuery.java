package com.example.order.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * com.example.order.entity.vo
 *
 * @author xzwnp
 * 2022/6/8
 * 17:26
 */
@Data
public class FoodQuery {
    @ApiModelProperty("食品编号")
    private Integer foodId;

    private int count;

}
