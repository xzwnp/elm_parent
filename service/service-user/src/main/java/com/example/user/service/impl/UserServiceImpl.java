package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import com.example.util.JwtEntity;
import com.example.util.JwtUtil;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * com.example.shirojwtdemo.service.impl
 *
 * @author xiaozhiwei
 * 2022/11/28
 * 15:20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, IService<User> {

    @Override
    public User findUserByUsername(String account) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, account);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public String login(String username, String password) {
        //1.用户名密码检验
        //username和password如果为空,controller背锅
        User user = findUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        if (!StringUtils.hasLength(user.getPassword())) {
            throw new IncorrectCredentialsException("该用户未设置密码,请先使用验证码登录!");
        }
        String hashedPassword = new SimpleHash("md5", password, "salt", 3).toString();
        if (!hashedPassword.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码错误!");
        }
        //2.登录成功,生成token
        JwtEntity jwtEntity = new JwtEntity();
        jwtEntity.setUserId(user.getId());
        jwtEntity.setUsername(user.getUsername());
        jwtEntity.setRoles(Arrays.asList("user"));

        return JwtUtil.createJwtToken(jwtEntity);
    }


}
