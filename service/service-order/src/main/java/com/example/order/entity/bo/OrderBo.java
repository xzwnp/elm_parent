package com.example.order.entity.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.order.entity.OrderFood;
import com.example.order.entity.vo.FoodVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * com.example.order.entity.bo
 *
 * @author xzwnp
 * 2022/6/9
 * 14:33
 */
@Data
public class OrderBo {
    private String id;

    private String businessId;

    private String businessName;

    @ApiModelProperty("0:未支付;1支付成功;2超时;3:退款")
    private Integer status;

    private BigDecimal totalPrice;

    private String userId;

    private LocalDateTime createTime;

    List<OrderFood> orderFoodList;
}
