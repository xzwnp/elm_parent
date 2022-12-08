package com.example.admin.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * com.example.admin.entity.dto
 *
 * @author xiaozhiwei
 * 2022/12/7
 * 19:27
 */
@Data
public class AdminInfoDto {
    private String username;
    private String nickname;
    private List<String> roles;

    public AdminInfoDto(String username, String nickname, List<String> roles) {
        this.username = username;
        this.nickname = nickname;
        this.roles = roles;
    }
}
