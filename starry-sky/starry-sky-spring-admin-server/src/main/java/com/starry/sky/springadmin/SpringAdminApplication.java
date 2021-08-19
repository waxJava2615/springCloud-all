package com.starry.sky.springadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wax
 * @description: spring admin 监控启动类
 * @date 2021-08-18
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class SpringAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAdminApplication.class,args);
    }


}
