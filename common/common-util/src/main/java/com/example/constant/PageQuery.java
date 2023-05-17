package com.example.constant;

import lombok.Data;

/**
 * com.example.constant
 *
 * @author xzwnp
 * 2022/12/6
 * 14:42
 */
@Data
public class PageQuery {
    protected Integer page = 1;
    protected Integer pageSize = 10;
    protected String keyword;
}
