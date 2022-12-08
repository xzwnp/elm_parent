package com.example.business.entity.query;

import com.example.constant.PageQuery;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * com.example.business.entity.query
 *
 * @author xiaozhiwei
 * 2022/12/7
 * 13:18
 */
@Getter
@Setter
public class FoodPageQuery extends PageQuery {
    private String businessId;
}
