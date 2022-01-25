package com.starry.sky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author wax
 * @description: 启动类
 * @date 2021-08-19
 */
@EnableWebMvc
@MapperScan(basePackages = "com.starry.sky.infrastructure.orm.base")
@SpringBootApplication
public class StarrySkyAdminServer {
    public static void main(String[] args) {
        SpringApplication.run(StarrySkyAdminServer.class,args);
    }
}
