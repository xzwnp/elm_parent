package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.ResultCode;
import com.example.entity.UserInputException;
import com.example.user.entity.User;
import com.example.util.JwtUtils;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.example.user.entity.Admin;
import com.example.user.mapper.AdminMapper;
import com.example.user.service.AdminService;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;


@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param adminid 主键
     * @return 实例对象
     */
    @Override
    public Admin queryById(Integer adminid) {
        return adminMapper.selectById(adminid);
    }

    /**
     * 分页查询
     *
     * @param admin   筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    @Override

    public Page<Admin> paginQuery(Admin admin, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(admin.getAdminname())) {
            queryWrapper.eq(Admin::getAdminname, admin.getAdminname());
        }
        if (StringUtils.hasLength(admin.getPassword())) {
            queryWrapper.eq(Admin::getPassword, admin.getPassword());
        }
        //2. 执行分页查询
        Page<Admin> pagin = new Page<>(current, size, true);
        IPage<Admin> selectResult = adminMapper.selectByPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin insert(Admin admin) {
        adminMapper.insert(admin);
        return admin;
    }

    /**
     * 更新数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin update(Admin admin) {
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<Admin> chainWrapper = new LambdaUpdateChainWrapper<Admin>(adminMapper);
        if (StringUtils.hasLength(admin.getAdminname())) {
            chainWrapper.eq(Admin::getAdminname, admin.getAdminname());
        }
        if (StringUtils.hasLength(admin.getPassword())) {
            chainWrapper.eq(Admin::getPassword, admin.getPassword());
        }
        //2. 设置主键，并更新
        chainWrapper.set(Admin::getAdminid, admin.getAdminid());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if (ret) {
            return queryById(admin.getAdminid());
        } else {
            return admin;
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param adminid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer adminid) {
        int total = adminMapper.deleteById(adminid);
        return total > 0;
    }

    @Override
    public String login(Admin user) {
        String username = user.getAdminname();
        String password = user.getPassword();

        //校验参数
        if (StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(username)) {
            throw new UserInputException(ResultCode.INPUT_ERROR, "请检查输入!");
        }

        //获取会员
        Admin result = adminMapper.selectOne(new QueryWrapper<Admin>().eq("adminname", username));
        if (null == result) {
            throw new UserInputException(ResultCode.INPUT_ERROR, "用户不存在!");
        }


        //校验密码
        System.out.println(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        if (!DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(result.getPassword())) {
            throw new UserInputException(ResultCode.INPUT_ERROR, "密码错误!");
        }

        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(result.getAdminid().toString(), result.getAdminname());
        return token;
    }
}