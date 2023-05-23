package com.example.util;

import com.example.constant.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * com.example.entity
 *
 * @author xzwnp
 * 2022/5/27
 * 22:44
 */
@EqualsAndHashCode(callSuper = true) //生成equal和hashcode时包含父类的属性
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInputException extends RuntimeException {
    private ResultCode code;
    private String msg;

    public UserInputException(String msg) {
        this.code=ResultCode.INPUT_ERROR;
        this.msg = msg;
    }
}
