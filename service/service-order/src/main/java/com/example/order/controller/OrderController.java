package com.example.order.controller;


import com.example.autoconfig.util.ShiroUtil;
import com.example.util.UserInputException;
import com.example.order.entity.bo.OrderBo;
import com.example.order.entity.query.OrderQuery;
import com.example.order.service.impl.OrderServiceImpl;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaozhiwei
 * @since 2022-06-08
 */
@RestController
@RequestMapping("")
@Api(tags = "订单查询,生成")
@RequiresAuthentication
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;


    /**
     *
     */
    @PostMapping("/generateOrder")
    public String generateOrder(@RequestBody OrderQuery orderQuery) {
        return orderService.generateOrder(orderQuery, String.valueOf(ShiroUtil.getUserId()));
    }

    @GetMapping("byType")
    public List<OrderBo> getOrders(@RequestParam(value = "type") Integer orderType) {
        return orderService.getOrders(orderType, String.valueOf(ShiroUtil.getUserId()));
    }

    @GetMapping("{id}")
    public OrderBo getOrderById(@PathVariable("id") String orderId) {
        if (orderId == null) {
            throw new UserInputException("订单号为空!");
        }
        return orderService.getOrderById(orderId);
    }

}
