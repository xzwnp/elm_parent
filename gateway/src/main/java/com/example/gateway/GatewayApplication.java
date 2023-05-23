package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * com.example.gateway
 *
 * @author xzwnp
 * 2022/4/5
 * 12:05
 * Steps：
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableOpenApi
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);

    }
}
