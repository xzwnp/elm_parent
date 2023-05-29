package com.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.entity.Role;
import com.example.admin.entity.User;
import com.example.admin.mapper.UserMapper;
import com.example.admin.service.PermissionService;
import com.example.admin.service.RoleService;
import com.example.admin.service.UserService;
import com.example.util.JwtEntity;
import com.example.util.JwtUtil;
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
	public User findUserByUserName(String username) {
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(User::getUsername, username);
		return baseMapper.selectOne(wrapper);
	}

	@Override
	public String login(String username, String password) {
		//1.用户名密码检验
		//username和password如果为空,controller背锅
		User user = findUserByUserName(username);
		if (user == null) {
			throw new UnknownAccountException("用户不存在!");
		}
		String hashedPassword = new SimpleHash("md5", password, "salt", 3).toString();
		if (!hashedPassword.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("密码错误!");
		}
		//2.登录成功,查用户权限,生成token
		JwtEntity jwtEntity = new JwtEntity();
		jwtEntity.setUserId(user.getId());
		jwtEntity.setUsername(user.getUsername());
		jwtEntity.setNickname(user.getNickname());
		//2.1 查角色
		List<Role> roles = roleService.findRoleByUserId(user.getId());
		List<Integer> roleIdList = roles.stream().map(Role::getId).collect(Collectors.toList());
		List<String> roleStringList = roles.stream().map(Role::getRole).collect(Collectors.toList());
		jwtEntity.setRoles(roleStringList);
		//2.2 查权限
		List<String> permissionList = permissionService.findPermissionsByRoleIds(roleIdList);
		jwtEntity.setPermissions(permissionList);

		return JwtUtil.createJwtToken(jwtEntity);
	}
}
