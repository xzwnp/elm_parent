package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.user.entity.User;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-03-15
 */
public interface UserService extends IService<User> {
    String login(User admin);

    String login(String phone,String code);
}
