package com.example.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName user
 */
@TableName(value = "admin")
@Data
public class User implements Serializable {
    /**
     *
     */
    @TableId
    private Integer id;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    private String nickname;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}