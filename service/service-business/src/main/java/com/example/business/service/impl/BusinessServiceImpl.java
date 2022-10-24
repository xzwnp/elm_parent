package com.example.business.service.impl;

import com.example.business.entity.Business;
import com.example.business.mapper.BusinessMapper;
import com.example.business.service.BusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaozhiwei
 * @since 2022-05-23
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {

}
