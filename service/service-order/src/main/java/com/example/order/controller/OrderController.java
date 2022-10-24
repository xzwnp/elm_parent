package com.example.order.controller;


import com.example.entity.UserInputException;
import com.example.order.entity.bo.OrderBo;
import com.example.order.entity.query.OrderQuery;
import com.example.order.service.impl.OrderServiceImpl;
import com.example.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;


    @PostMapping("/generateOrder")
    public String generateOrder(@RequestBody OrderQuery orderQuery, HttpServletRequest request) {
        String userId = JwtUtils.getUserIdByJwtToken(request);
        return orderService.generateOrder(orderQuery, userId);
    }

    @GetMapping("byType")
    public List<OrderBo> getOrders(@RequestParam(value = "type") Integer orderType, HttpServletRequest request) {
        String userId = JwtUtils.getUserIdByJwtToken(request);

        return orderService.getOrders(orderType, userId);
    }

    @GetMapping("")
    public OrderBo getOrderById(@RequestParam("id") String orderId) {
        if (orderId==null){
            throw new UserInputException("订单号为空!");
        }
        return orderService.getOrderById(orderId);
    }
}
