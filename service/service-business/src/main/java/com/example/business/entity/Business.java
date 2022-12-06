package com.example.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaozhiwei
 * @since 2022-05-23
 */
@Getter
@Setter
@TableName("business")
@ApiModel(value = "Business对象", description = "")
public class Business implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商家编号")
    @TableId(value = "businessId", type = IdType.AUTO)
    private Integer businessId;

    @TableField("password")
    private String password;

    @ApiModelProperty("商家名称")
    @TableField("businessName")
    private String businessName;

    @ApiModelProperty("商家地址")
    @TableField("businessAddress")
    private String businessAddress;

    @ApiModelProperty("商家")
    @TableField("businessExplain")
    private String businessExplain;

    @ApiModelProperty("起送费")
    @TableField("startPrice")
    private BigDecimal startPrice;

    @ApiModelProperty("配送费")
    @TableField("deliveryPrice")
    private BigDecimal deliveryPrice;

    @TableField("cover")
    private String cover;

    @TableField("score")
    private Float score;

    @ApiModelProperty("折扣")
    @TableField("discounts")
    private String discounts;

    @TableField("sellCount")
    private Integer sellCount;

    @ApiModelProperty("热门评论")
    @TableField("hotComment")
    private String hotComment;

    @ApiModelProperty("红包")
    @TableField("redPacket")
    private String redPacket;


    @TableField("type")
    private String type;

}
