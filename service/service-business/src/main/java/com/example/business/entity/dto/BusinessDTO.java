package com.example.business.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2022-12-06
 */
@Data
@ApiModel(value = "")
public class BusinessDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "商家编号")
	private Integer businessid;

	@ApiModelProperty(value = "")
	private String password;

	@ApiModelProperty(value = "商家名称")
	private String businessname;

	@ApiModelProperty(value = "商家地址")
	private String businessaddress;

	@ApiModelProperty(value = "商家介绍")
	private String businessexplain;

	@ApiModelProperty(value = "商家图片")
	private String cover;

	@ApiModelProperty(value = "点餐分类")
	private String type;

	@ApiModelProperty(value = "起送费")
	private BigDecimal starprice;

	@ApiModelProperty(value = "配送费")
	private BigDecimal deliveryprice;

	@ApiModelProperty(value = "备注")
	private String remarks;

	@ApiModelProperty(value = "红包")
	private BigDecimal redpacket;

	@ApiModelProperty(value = "评分")
	private BigDecimal score;

	@ApiModelProperty(value = "折扣")
	private String discounts;

	@ApiModelProperty(value = "销量")
	private Integer sellcount;

	@ApiModelProperty(value = "热门评论")
	private String hotcomment;


}