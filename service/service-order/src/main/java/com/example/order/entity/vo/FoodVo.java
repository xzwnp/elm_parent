package com.example.order.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * com.example.order.entity.vo
 *
 * @author xzwnp
 * 2022/6/8
 * 17:26
 */
@Data
public class FoodVo {
    @ApiModelProperty("食品编号")
    private Integer foodId;

    @ApiModelProperty("食品名称")
    private String foodName;

    @ApiModelProperty("食品价格")
    private BigDecimal foodPrice;

    private int count;

}
