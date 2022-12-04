package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.entity.Role;

import java.util.List;

/**
* @author xzwnp
* @description 针对表【role】的数据库操作Mapper
* @createDate 2022-11-08 19:41:14
* @Entity com.example.user.entity.Role
*/
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> findRoleByUserId(Integer userId);
}




