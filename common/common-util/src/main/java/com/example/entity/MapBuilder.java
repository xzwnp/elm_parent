package com.example.entity;

import javafx.util.Builder;

import java.util.HashMap;
import java.util.Map;

/**
 * com.example.entity
 *
 * @author xzwnp
 * 2022/5/28
 * 11:37
 */
public class MapBuilder implements Builder<Map<String,Object>> {
    Map<String, Object> map;

    public MapBuilder() {
        this.map = new HashMap<>();
    }

    public MapBuilder put(String key, Object value) {
        this.map.put(key,value);
        return this;
    }

    @Override
    public Map<String, Object> build() {
        return this.map;
    }
}
