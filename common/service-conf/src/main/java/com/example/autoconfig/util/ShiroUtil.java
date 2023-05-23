package com.example.autoconfig.util;

import com.example.util.JwtEntity;
import org.apache.shiro.SecurityUtils;

/**
 * edu.ynu.se.xiecheng.achitectureclass.common.utils
 *
 * @author xzwnp
 * 2022/12/20
 * 18:51
 */
public class ShiroUtil {

    public static Integer getUserId() {
        return getUser().getUserId();
    }

    public static JwtEntity getUser() {
        return (JwtEntity) SecurityUtils.getSubject().getPrincipal();
    }

    public static boolean isCustomer(){
        return SecurityUtils.getSubject().hasRole("customer");
    }
}
