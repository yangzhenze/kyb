package com.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zzy
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SystemJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemJwtApplication.class, args);
    }
}
