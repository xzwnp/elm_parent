package com.example.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * com.example.core
 *
 * @author xzwnp
 * 2022/4/12
 * 11:34
 * Steps：
 */
@SpringBootApplication
//@EnableDiscoveryClient
@ComponentScan("com.example")
@MapperScan("com.example.business.mapper")
@EnableCaching
public class BusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class,args);
    }
}
