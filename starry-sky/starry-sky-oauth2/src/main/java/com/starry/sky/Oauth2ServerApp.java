package com.starry.sky;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wax
 * @description: 授权服务启动类
 * @date 2021-10-25
 */
@SpringBootApplication
@DubboComponentScan
public class Oauth2ServerApp {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApp.class, args);
    }

}
