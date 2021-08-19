package com.starry.sky;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author wax
 * @description: 启动类
 * @date 2021-08-19
 */
@SpringCloudApplication
public class StarrySkyAdminServer {
    public static void main(String[] args) {
        SpringApplication.run(StarrySkyAdminServer.class,args);
    }
}
