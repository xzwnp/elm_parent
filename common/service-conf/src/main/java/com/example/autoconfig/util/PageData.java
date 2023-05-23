/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.example.autoconfig.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 */
@Data
@ApiModel(value = "分页数据")
public class PageData<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "总记录数")
	private long total;

	@ApiModelProperty(value = "列表数据")
	private List<T> list;

	/**
	 * 分页
	 * @param list   列表数据
	 * @param total  总记录数
	 */
	public PageData(List<T> list, long total) {
		this.list = list;
		this.total = total;
	}

	public PageData(IPage<T> page) {
		this.list = page.getRecords();
		this.total = page.getTotal();
	}
}