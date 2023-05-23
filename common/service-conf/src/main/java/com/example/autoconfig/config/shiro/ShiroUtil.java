package com.example.autoconfig.config.shiro;

import com.example.util.JwtEntity;
import org.apache.shiro.SecurityUtils;

/**
 * @author xzwnp
 * 2022/12/20
 * 18:51
 */
public class ShiroUtil {

	public static Integer getUserId() {
		return (Integer) getUser().getUserId();
	}

	public static String getUsername() {
		return getUser().getUsername();
	}

	public static JwtEntity getUser() {
		return (JwtEntity) SecurityUtils.getSubject().getPrincipal();
	}
}
