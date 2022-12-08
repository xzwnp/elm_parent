package com.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.admin.entity.Permission;

import java.util.List;

/**
 * @author xzwnp
 * @description 针对表【permission】的数据库操作Service
 * @createDate 2022-11-08 19:41:14
 */
public interface PermissionService extends IService<Permission> {
    List<String> findPermissionsByRoleIds(List<Integer> roleIds);
}
