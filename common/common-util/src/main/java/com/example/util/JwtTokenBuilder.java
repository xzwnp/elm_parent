package com.example.util;

import javafx.util.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * com.example.util
 *
 * @author xiaozhiwei
 * 2023/5/10
 * 11:57
 */
public class JwtTokenBuilder implements Builder<String> {
    private final JwtEntity entity;

    public JwtTokenBuilder() {
        this.entity = new JwtEntity();
    }

    public JwtTokenBuilder setUserId(Integer userId) {
        entity.setUserId(userId);
        return this; //方便进行链式调用
    }

    public JwtTokenBuilder setUsername(String username) {
        entity.setUsername(username);
        return this; //方便进行链式调用
    }

    public JwtTokenBuilder setNickname(String nickname) {
        entity.setNickname(nickname);
        return this; //方便进行链式调用
    }

    public JwtTokenBuilder addPermission(String permission) {
        List<String> permissions = Optional.ofNullable(entity.getPermissions()).orElseGet(ArrayList::new);
        permissions.add(permission);
        return this;
    }

    public JwtTokenBuilder addPermission(List<String> newPermissions) {
        if (entity.getPermissions() == null) {
            entity.setPermissions(newPermissions);
        } else {
            entity.getPermissions().addAll(newPermissions);
        }
        return this;
    }

    public JwtTokenBuilder addRole(String role) {
        List<String> roles = Optional.ofNullable(entity.getRoles()).orElseGet(ArrayList::new);
        roles.add(role);
        return this;
    }

    public JwtTokenBuilder addRole(List<String> newRoles) {
        if (entity.getRoles() == null) {
            entity.setRoles(newRoles);
        } else {
            entity.getRoles().addAll(newRoles);
        }
        return this;
    }

    /**
     * 生成一个token
     */
    @Override
    public String build() {
        assert entity.getUserId() != null;
        assert entity.getUsername() != null;
        //JwtUtil.createJwtToken是一个工厂方法
        return JwtUtil.createJwtToken(entity);
    }
}
