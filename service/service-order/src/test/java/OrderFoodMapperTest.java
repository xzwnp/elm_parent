import com.example.order.OrderApplication;
import com.example.order.entity.Order;
import com.example.order.entity.OrderFood;
import com.example.order.entity.bo.OrderBo;
import com.example.order.entity.query.OrderQuery;
import com.example.order.mapper.OrderFoodMapper;
import com.example.order.mapper.OrderMapper;
import com.example.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * PACKAGE_NAME
 *
 * @author xzwnp
 * 2022/6/9
 * 11:36
 */
@SpringBootTest(classes = OrderApplication.class)
public class OrderFoodMapperTest {
    @Autowired
    OrderFoodMapper orderFoodMapper;
    @Autowired
    OrderMapper orderMapper;

    @Test
    @Transactional
    public void saveTest() {
        String orderId = RandomUtil.randomDatesSn(20);
        List<OrderFood> orderFoodList = new ArrayList<>();
        orderFoodList.add(new OrderFood(orderId, 1, new BigDecimal("19.9"), 1, "小笼包"));
        orderFoodList.add(new OrderFood(orderId, 2, new BigDecimal("29.9"), 2, "大笼包"));
        orderFoodMapper.insertBatch(orderFoodList);
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setBusinessId("123");
        orderQuery.setBusinessName("包子铺");
        orderQuery.setTotalPrice(new BigDecimal("99.9"));
        Order order = new Order(orderId, orderQuery.getBusinessId(), orderQuery.getBusinessName(),
                1, orderQuery.getTotalPrice(), "123", LocalDateTime.now());
        orderMapper.insert(order);
    }

    @Test
    void test2() {
        List<OrderBo> fullOrderInfoList = orderMapper.getFullOrderInfoList(null, "456");
        System.out.println(fullOrderInfoList);
    }
        @Test
    void test3() {
        OrderBo fullOrderInfoList = orderMapper.getFullOrderInfoByOrderId("20220609150732150444");
        System.out.println(fullOrderInfoList);
    }

}
