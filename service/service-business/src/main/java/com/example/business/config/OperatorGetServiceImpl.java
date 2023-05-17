package com.example.business.config;

import com.example.autoconfig.util.UserUtil;
import com.example.util.JwtEntity;
import icu.ynu.log.entity.Operator;
import icu.ynu.log.operator.IOperatorGetService;
import org.springframework.stereotype.Component;

/**
 * com.example.business.config
 *
 * @author xiaozhiwei
 * 2023/5/10
 * 14:42
 */
@Component
public class OperatorGetServiceImpl implements IOperatorGetService {
    @Override
    public Operator getOperator() {
        JwtEntity user = UserUtil.getUser();
        return new Operator(String.valueOf(user.getUserId()), user.getNickname());
    }
}
