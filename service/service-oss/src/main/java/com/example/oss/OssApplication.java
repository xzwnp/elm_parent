package com.example.oss;

import org.apache.shiro.spring.boot.autoconfigure.ShiroAnnotationProcessorAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * com.example.oss
 *
 * @author xzwnp
 * 2022/1/28
 * 18:32
 * 说明:没有配置数据源,所以要排除数据源自动配置类
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, ShiroAnnotationProcessorAutoConfiguration.class})
@ComponentScan(basePackages = {"com.example"})
@EnableDiscoveryClient
@RefreshScope
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
