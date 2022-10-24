package com.example.order.mapper;

import com.example.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.order.entity.bo.OrderBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaozhiwei
 * @since 2022-06-08
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    List<OrderBo> getFullOrderInfoList(@Param("status") Integer status, @Param("userId") String userId);

    OrderBo getFullOrderInfoByOrderId(@Param("orderId") String orderId);

}
