package com.example.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.user.entity.Admin;

public interface AdminService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param adminid 主键
     * @return 实例对象
     */
    Admin queryById(Integer adminid);
    
    /**
     * 分页查询
     *
     * @param admin 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<Admin> paginQuery(Admin admin, long current, long size);
    /** 
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin insert(Admin admin);
    /** 
     * 更新数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin update(Admin admin);
    /** 
     * 通过主键删除数据
     *
     * @param adminid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer adminid);

    String login(Admin admin);
}