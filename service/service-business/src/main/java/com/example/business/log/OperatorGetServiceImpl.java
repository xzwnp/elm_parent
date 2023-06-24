//package com.example.business.log;
//
//import com.example.autoconfig.util.ShiroUtil;
//import com.example.util.JwtEntity;
//import icu.ynu.log.entity.Operator;
//import icu.ynu.log.operator.IOperatorGetService;
//
///**
// * com.example.business.log
// *
// * @author xiaozhiwei
// * 2023/5/23
// * 23:21
// */
//public class OperatorGetServiceImpl implements IOperatorGetService {
//    @Override
//    public Operator getOperator() {
//        JwtEntity user = ShiroUtil.getUser();
//        return new Operator(user.getUserId().toString(), user.getNickname());
//    }
//}
