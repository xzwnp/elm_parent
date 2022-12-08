package com.example.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@TableName("orders")
@ApiModel(value = "Order对象", description = "")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("businessId")
    private String businessId;

    @TableField("businessName")
    private String businessName;

    @ApiModelProperty("0:未支付;1支付成功;2超时;3:退款")
    @TableField("status")
    private Integer status;

    @TableField("totalPrice")
    private BigDecimal totalPrice;

    @TableField("userId")
    private String userId;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


}
