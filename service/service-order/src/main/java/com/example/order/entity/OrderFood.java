package com.example.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaozhiwei
 * @since 2022-06-08
 */
@Getter
@Setter
@TableName("order_food")
@ApiModel(value = "OrderFood对象", description = "")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderFood implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("orderId")
    private String orderId;

    @TableField("foodId")
    private Integer foodId;

    private BigDecimal foodPrice;

    private int foodCount;

    private String foodName;


}
