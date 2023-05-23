package com.example.order.service.impl;

import com.example.entity.R;
import com.example.constant.ResultCode;
import com.example.entity.dto.FoodDto;
import com.example.order.entity.Order;
import com.example.order.entity.OrderFood;
import com.example.order.entity.bo.OrderBo;
import com.example.order.entity.query.OrderQuery;
import com.example.order.entity.vo.FoodQuery;
import com.example.order.mapper.OrderFoodMapper;
import com.example.order.mapper.OrderMapper;
import com.example.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.order.service.client.BusinessClient;
import com.example.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    BusinessClient businessClient;

	public OrderServiceImpl(OrderFoodMapper orderFoodMapper, BusinessClient businessClient) {
		this.orderFoodMapper = orderFoodMapper;
		this.businessClient = businessClient;
	}

	@Transactional
    @Override
    public String generateOrder(OrderQuery orderQuery, String userId) {
        FoodQuery[] foods = orderQuery.getFoodList();

        //远程调用 查商品价格 防止前端篡改数据
        List<Integer> foodIdList = Arrays.stream(foods).map(FoodQuery::getFoodId).collect(Collectors.toList());
        R<List<FoodDto>> res = businessClient.queryFoodByFoodIds(foodIdList);
        if (res.getCode() != ResultCode.SUCCESS.getCode()) {
            throw new RuntimeException("获取食物信息失败!");
        }
        List<FoodDto> foodInfoList = res.getData();
        //list转map,方便查找
        Map<Integer, FoodDto> foodInfoMap = foodInfoList.stream().collect(Collectors.toMap(FoodDto::getFoodId, item -> item, (k1, k2) -> k1));
        String orderId = RandomUtil.randomDatesSn(20);

        //创建订单项,同时计算总价
        BigDecimal totalPrice = new BigDecimal(0);
        List<OrderFood> orderFoodList = Arrays.stream(foods).map(
                foodVo -> {

                    OrderFood orderFood = new OrderFood();
                    FoodDto foodInfo = foodInfoMap.get(foodVo.getFoodId());

                    BeanUtils.copyProperties(foodInfo, orderFood);
                    orderFood.setFoodCount(foodVo.getCount());
                    orderFood.setOrderId(orderId);
                    //计算总价
                    totalPrice.add(foodInfo.getFoodPrice().multiply(new BigDecimal(foodVo.getCount())));
                    return orderFood;
                }
        ).collect(Collectors.toList());


        //执行插入
        orderFoodMapper.insertBatch(orderFoodList);
        Order order = new Order(orderId, orderQuery.getBusinessId(), orderQuery.getBusinessName(),
                0, totalPrice, userId, LocalDateTime.now());
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
