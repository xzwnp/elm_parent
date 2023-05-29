package com.example.autoconfig.util;

import com.example.constant.ResultCode;
import com.example.entity.GlobalException;
import com.example.util.JwtEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

/**
 * com.example.autoconfig.util
 *
 * @author xiaozhiwei
 * 2023/5/10
 * 14:43
 */
@Slf4j
public class UserUtil {
    public static Integer getUserId() {
        JwtEntity user = getUser();
        return (Integer) user.getUserId();
    }

    public static JwtEntity getUser() {
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            throw new GlobalException(ResultCode.ERROR, "用户未登录!无法获取用户信息");
        }
        try {
            JwtEntity user = (JwtEntity) (SecurityUtils.getSubject().getPrincipal());
            return user;
        } catch (Exception e) {
            throw new GlobalException(ResultCode.ERROR, "无法获取用户信息");
        }


    }
}
