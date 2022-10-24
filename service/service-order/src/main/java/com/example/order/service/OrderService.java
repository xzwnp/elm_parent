package com.example.order.service;

import com.example.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.order.entity.bo.OrderBo;
import com.example.order.entity.query.OrderQuery;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaozhiwei
 * @since 2022-06-08
 */
public interface OrderService extends IService<Order> {

    String generateOrder(OrderQuery orderQuery, String userId);

    List<OrderBo> getOrders(Integer orderType, String userId);

    OrderBo getOrderById(String orderId);
}
