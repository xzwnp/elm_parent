package com.example.util;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

/**
 * com.example.shirojwtdemo.util
 *
 * @author xiaozhiwei
 * 2022/11/28
 * 15:38
 */
@Data
public class JwtEntity {
    private Integer userId;
    private String username;
    private String nickname;
    private List<String> roles;
    private List<String> permissions;

}
