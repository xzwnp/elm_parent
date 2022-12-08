package com.example.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
    private Integer id;
    /**
     * 用户名/手机号
     */
    @TableField("phone")
    private String username;
    private String nickname;
    private String password;
}
