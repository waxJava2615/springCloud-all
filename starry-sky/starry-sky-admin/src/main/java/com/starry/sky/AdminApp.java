package com.starry.sky;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wax
 * @description: 后台管理系统
 * @date 2021-11-09
 */
//@EnableWebMvc
@EnableFeignClients
@SpringBootApplication
@MapperScan(basePackages = "com.starry.sky.infrastructure.orm.base")
@EnableDubbo(scanBasePackages = "com.starry.sky.interfaces.dubbo")
public class AdminApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class,args);
//        AntPathMatcher antPathMatcher = new AntPathMatcher("/admin/abc/**");
//        System.out.println(antPathMatcher.isPattern("/admin/**")); // true
//        AntPathMatcher antPathMatcher = new AntPathMatcher();
//        System.out.println(antPathMatcher.match("/admin/user/list","/user/list")); // false

    }
}
