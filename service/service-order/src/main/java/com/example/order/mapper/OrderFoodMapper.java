package com.example.order.mapper;

import com.example.order.entity.Order;
import com.example.order.entity.OrderFood;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaozhiwei
 * @since 2022-06-08
 */
@Mapper
public interface OrderFoodMapper extends BaseMapper<OrderFood> {
    void insertBatch(@Param("orderFoodList") List<OrderFood>orderFoodList);
}
