package com.example.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.example.educenter.entity
 *
 * @author xzwnp
 * 2022/5/17
 * 11:06
 */
@TableName("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String phone;
    private String nickname;
    private String password;
}