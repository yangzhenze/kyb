package com.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zzy
 */
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@EnableCaching
public class SystemAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemAdminApplication.class, args);
	}
}
