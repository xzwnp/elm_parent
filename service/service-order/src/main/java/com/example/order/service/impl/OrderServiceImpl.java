package com.example.order.service.impl;

import com.example.order.entity.Order;
import com.example.order.entity.OrderFood;
import com.example.order.entity.bo.OrderBo;
import com.example.order.entity.query.OrderQuery;
import com.example.order.entity.vo.FoodVo;
import com.example.order.mapper.OrderFoodMapper;
import com.example.order.mapper.OrderMapper;
import com.example.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaozhiwei
 * @since 2022-06-08
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    OrderFoodMapper orderFoodMapper;

    public OrderServiceImpl(OrderFoodMapper orderFoodMapper) {
        this.orderFoodMapper = orderFoodMapper;
    }

    @Transactional
    @Override
    public String generateOrder(OrderQuery orderQuery, String userId) {
        log.info(orderQuery.toString());
        FoodVo[] foods = orderQuery.getFoodList();
        String orderId = RandomUtil.randomDatesSn(20);
        List<OrderFood> orderFoodList = Arrays.stream(foods).map(
                foodVo -> {
                    OrderFood orderFood = new OrderFood();
                    BeanUtils.copyProperties(foodVo, orderFood);
                    orderFood.setFoodCount(foodVo.getCount());
                    orderFood.setOrderId(orderId);
                    return orderFood;
                }
        ).collect(Collectors.toList());

        orderFoodMapper.insertBatch(orderFoodList);
        Order order = new Order(orderId, orderQuery.getBusinessId(), orderQuery.getBusinessName(),
                0, orderQuery.getTotalPrice(), userId, LocalDateTime.now());
        baseMapper.insert(order);

        return orderId;
    }

    @Override
    public List<OrderBo> getOrders(Integer orderType, String userId) {
        List<OrderBo> fullOrderInfoList = baseMapper.getFullOrderInfoList(orderType, userId);
        return fullOrderInfoList;
    }

    @Override
    public OrderBo getOrderById(String orderId) {
        return baseMapper.getFullOrderInfoByOrderId(orderId);
    }


}
