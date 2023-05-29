package com.example.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * com.example.entity.dto
 *
 * @author xzwnp
 * 2022/12/27
 * 14:58
 */
@Getter
@Setter
public class FoodDto {
    @ApiModelProperty("食品编号")
    private Integer foodId;

    @ApiModelProperty("食品名称")
    private String foodName;

    @ApiModelProperty("食品介绍")
    private String foodExplain;

    @ApiModelProperty("食品介绍")
    private BigDecimal foodPrice;

    @ApiModelProperty("所属商家编号")
    private Integer businessId;

    @ApiModelProperty("原价")
    private BigDecimal originPrice;

    @ApiModelProperty("封面")
    private String cover;
}
