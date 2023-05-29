package com.example.entity;

import lombok.Data;

import java.util.HashMap;

/**
 * edu.ynu.se.xiecheng.achitectureclass.common.utils
 *
 * @author xzwnp
 * 2022/12/20
 * 15:59
 */
@Data
public class ResultBody extends HashMap<String, Object> {
    @Override
    public ResultBody put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public ResultBody() {
        super();
    }

}
