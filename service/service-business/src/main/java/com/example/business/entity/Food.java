package com.example.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaozhiwei
 * @since 2022-04-12
 */
@Getter
@Setter
@TableName("food")
@ApiModel(value = "Food对象", description = "")
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("食品编号")
    @TableId(value = "foodId", type = IdType.AUTO)
    private Integer foodId;

    @ApiModelProperty("食品名称")
    @TableField("foodName")
    private String foodName;

    @ApiModelProperty("食品介绍")
    @TableField("foodExplain")
    private String foodExplain;

    @ApiModelProperty("食品价格")
    @TableField("foodPrice")
    private BigDecimal foodPrice;

    @ApiModelProperty("所属商家编号")
    @TableField("businessId")
    private Integer businessId;

    @ApiModelProperty("原价")
    @TableField("originPrice")
    private BigDecimal originPrice;

    @ApiModelProperty("封面")
    @TableField("cover")
    private String cover;

}
