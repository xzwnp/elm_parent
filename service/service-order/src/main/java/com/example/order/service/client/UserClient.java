package com.example.order.service.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * com.example.order.service.client
 *
 * @author xzwnp
 * 2022/6/8
 * 16:47
 */
@FeignClient("service-user")
public interface UserClient {

}
