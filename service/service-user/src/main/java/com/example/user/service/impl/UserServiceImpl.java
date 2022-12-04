package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.user.entity.Role;
import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.PermissionService;
import com.example.user.service.RoleService;
import com.example.user.service.UserService;
import com.example.util.JwtEntity;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    protected RoleService roleService;
    protected PermissionService permissionService;

    @Autowired
    public UserServiceImpl(RoleService roleService, PermissionService permissionService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

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
        String hashedPassword = new SimpleHash("md5", password, "salt", 3).toString();
        if (!hashedPassword.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码错误!");
        }
        //2.登录成功,查用户权限,生成token
        JwtEntity jwtEntity = new JwtEntity();
        jwtEntity.setUserId(String.valueOf(user.getId()));
        jwtEntity.setUserName(user.getUsername());
        //2.1 查角色
        List<Role> roles = roleService.findRoleByUserId(user.getId());
        List<Integer> roleIdList = roles.stream().map(Role::getId).collect(Collectors.toList());
        List<String> roleStringList = roles.stream().map(Role::getRole).collect(Collectors.toList());
        jwtEntity.setRoles(roleStringList);
        //2.2 查权限
        List<String> permissionList = permissionService.findPermissionsByRoleIds(roleIdList);
        //todo 查询用户对应的权限
        jwtEntity.setPermissions(permissionList);

        return com.example.demo.util.JwtUtil.createJwtToken(jwtEntity);
    }
}
