package com.example.order.service.impl;

import com.example.entity.R;
import com.example.entity.dto.FoodDto;
import com.example.order.entity.OrderFood;
import com.example.order.entity.bo.OrderBo;
import com.example.order.entity.query.OrderQuery;
import com.example.order.entity.vo.FoodQuery;
import com.example.order.mapper.OrderFoodMapper;
import com.example.order.service.client.BusinessClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderFoodMapper mockOrderFoodMapper;
    @Mock
    private BusinessClient mockBusinessClient;

    private OrderServiceImpl orderServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        orderServiceImplUnderTest = new OrderServiceImpl(mockOrderFoodMapper, mockBusinessClient);
    }

    @Test
    void testGenerateOrder() {
        // Setup
        final OrderQuery orderQuery = new OrderQuery();
        orderQuery.setBusinessName("businessName");
        orderQuery.setBusinessId("businessId");
        orderQuery.setTotalPrice(new BigDecimal("0.00"));
        final FoodQuery foodQuery = new FoodQuery();
        foodQuery.setFoodId(0);
        foodQuery.setCount(0);
        orderQuery.setFoodList(new FoodQuery[]{foodQuery});

        // Configure BusinessClient.queryFoodByFoodIds(...).
        final FoodDto foodDto = new FoodDto();
        foodDto.setFoodId(0);
        foodDto.setFoodName("foodName");
        foodDto.setFoodExplain("foodExplain");
        foodDto.setFoodPrice(new BigDecimal("0.00"));
        foodDto.setBusinessId(0);
        foodDto.setOriginPrice(new BigDecimal("0.00"));
        foodDto.setCover("cover");
        final R<List<FoodDto>> listR = R.success(Arrays.asList(foodDto));
        when(mockBusinessClient.queryFoodByFoodIds(Arrays.asList(0))).thenReturn(listR);

        // Run the test
        final String result = orderServiceImplUnderTest.generateOrder(orderQuery, "userId");

        // Verify the results
        assertThat(result).isEqualTo("result");
        verify(mockOrderFoodMapper).insertBatch(
                Arrays.asList(new OrderFood("orderId", 0, new BigDecimal("0.00"), 0, "foodName")));
    }

    @Test
    void testGenerateOrder_BusinessClientReturnsNoItem() {
        // Setup
        final OrderQuery orderQuery = new OrderQuery();
        orderQuery.setBusinessName("businessName");
        orderQuery.setBusinessId("businessId");
        orderQuery.setTotalPrice(new BigDecimal("0.00"));
        final FoodQuery foodQuery = new FoodQuery();
        foodQuery.setFoodId(0);
        foodQuery.setCount(0);
        orderQuery.setFoodList(new FoodQuery[]{foodQuery});

        when(mockBusinessClient.queryFoodByFoodIds(Arrays.asList(0))).thenReturn(R.success(new ArrayList<>()));

        // Run the test
        final String result = orderServiceImplUnderTest.generateOrder(orderQuery, "userId");

        // Verify the results
        assertThat(result).isEqualTo("result");
        verify(mockOrderFoodMapper).insertBatch(
                Arrays.asList(new OrderFood("orderId", 0, new BigDecimal("0.00"), 0, "foodName")));
    }

    @Test
    void testGenerateOrder_BusinessClientReturnsNoItems() {
        // Setup
        final OrderQuery orderQuery = new OrderQuery();
        orderQuery.setBusinessName("businessName");
        orderQuery.setBusinessId("businessId");
        orderQuery.setTotalPrice(new BigDecimal("0.00"));
        final FoodQuery foodQuery = new FoodQuery();
        foodQuery.setFoodId(0);
        foodQuery.setCount(0);
        orderQuery.setFoodList(new FoodQuery[]{foodQuery});

        when(mockBusinessClient.queryFoodByFoodIds(Arrays.asList(0))).thenReturn(R.success(Collections.emptyList()));

        // Run the test
        final String result = orderServiceImplUnderTest.generateOrder(orderQuery, "userId");

        // Verify the results
        assertThat(result).isEqualTo("result");
        verify(mockOrderFoodMapper).insertBatch(
                Arrays.asList(new OrderFood("orderId", 0, new BigDecimal("0.00"), 0, "foodName")));
    }

    @Test
    void testGenerateOrder_BusinessClientReturnsError() {
        // Setup
        final OrderQuery orderQuery = new OrderQuery();
        orderQuery.setBusinessName("businessName");
        orderQuery.setBusinessId("businessId");
        orderQuery.setTotalPrice(new BigDecimal("0.00"));
        final FoodQuery foodQuery = new FoodQuery();
        foodQuery.setFoodId(0);
        foodQuery.setCount(0);
        orderQuery.setFoodList(new FoodQuery[]{foodQuery});

        // Configure BusinessClient.queryFoodByFoodIds(...).
        final R<List<FoodDto>> listR = R.error();
        when(mockBusinessClient.queryFoodByFoodIds(Arrays.asList(0))).thenReturn(listR);

        // Run the test
        final String result = orderServiceImplUnderTest.generateOrder(orderQuery, "userId");

        // Verify the results
        assertThat(result).isEqualTo("result");
        verify(mockOrderFoodMapper).insertBatch(
                Arrays.asList(new OrderFood("orderId", 0, new BigDecimal("0.00"), 0, "foodName")));
    }

    @Test
    void testGetOrders() {
        // Setup
        final OrderBo orderBo = new OrderBo();
        orderBo.setId("id");
        orderBo.setBusinessId("businessId");
        orderBo.setBusinessName("businessName");
        orderBo.setStatus(0);
        orderBo.setTotalPrice(new BigDecimal("0.00"));
        orderBo.setUserId("userId");
        orderBo.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final OrderFood orderFood = new OrderFood();
        orderFood.setOrderId("orderId");
        orderFood.setFoodId(0);
        orderFood.setFoodPrice(new BigDecimal("0.00"));
        orderFood.setFoodCount(0);
        orderFood.setFoodName("foodName");
        orderBo.setOrderFoodList(Arrays.asList(orderFood));
        final List<OrderBo> expectedResult = Arrays.asList(orderBo);

        // Run the test
        final List<OrderBo> result = orderServiceImplUnderTest.getOrders(0, "userId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetOrderById() {
        // Setup
        final OrderBo expectedResult = new OrderBo();
        expectedResult.setId("id");
        expectedResult.setBusinessId("businessId");
        expectedResult.setBusinessName("businessName");
        expectedResult.setStatus(0);
        expectedResult.setTotalPrice(new BigDecimal("0.00"));
        expectedResult.setUserId("userId");
        expectedResult.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final OrderFood orderFood = new OrderFood();
        orderFood.setOrderId("orderId");
        orderFood.setFoodId(0);
        orderFood.setFoodPrice(new BigDecimal("0.00"));
        orderFood.setFoodCount(0);
        orderFood.setFoodName("foodName");
        expectedResult.setOrderFoodList(Arrays.asList(orderFood));

        // Run the test
        final OrderBo result = orderServiceImplUnderTest.getOrderById("orderId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}

