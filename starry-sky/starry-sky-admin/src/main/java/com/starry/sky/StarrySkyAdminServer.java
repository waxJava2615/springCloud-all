package com.starry.sky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wax
 * @description: 启动类
 * @date 2021-08-19
 */
@MapperScan(basePackages = "com.starry.sky.infrastructure.orm.repository")
@SpringBootApplication
public class StarrySkyAdminServer {
    public static void main(String[] args) {
        SpringApplication.run(StarrySkyAdminServer.class,args);
    }
}
