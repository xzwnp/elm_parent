package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.GlobalException;
import com.example.entity.ResultCode;
import com.example.entity.UserInputException;
import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import com.example.util.JwtUtils;
import com.example.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;


/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-03-15
 */
@Service
@Slf4j
public class UserServiceImpl
        extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public String login(User user) {
        String username = user.getPhone();
        String password = user.getPassword();

        //校验参数
        if (StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(username)) {
            throw new UserInputException(ResultCode.INPUT_ERROR, "请检查输入!");
        }

        //获取会员
        User result = baseMapper.selectOne(new QueryWrapper<User>().eq("phone", username));
        if (null == result) {
            throw new UserInputException(ResultCode.INPUT_ERROR, "用户不存在!");
        }


        //校验密码
        System.out.println(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        if (!DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(result.getPassword())) {
            throw new UserInputException(ResultCode.INPUT_ERROR, "密码错误!");
        }

        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(result.getId(), result.getPhone());
        return token;
    }

    @Override
    public String login(String phone, String code) {
        String c = (String) redisTemplate.opsForValue().get("VerificationCode::" + phone);
        if (c == null || !c.equals(code)) {
            throw new UserInputException("验证码不正确!");
        }
        User result = baseMapper.selectOne(new QueryWrapper<User>().eq("phone", phone));
//        创建用户
        if (result == null) {
            String id = RandomUtil.randomUUID();
            result = new User(id, phone, "饿了么用户", null);
            baseMapper.insert(result);

        }
        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(result.getId(), result.getPhone());
        return token;
    }


}
