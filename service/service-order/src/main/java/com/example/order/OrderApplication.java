package com.example.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * com.example.order
 *
 * @author xzwnp
 * 2022/6/8
 * 10:10
 */
@SpringBootApplication
@MapperScan("com.example.order.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement
@ComponentScan("com.example")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
