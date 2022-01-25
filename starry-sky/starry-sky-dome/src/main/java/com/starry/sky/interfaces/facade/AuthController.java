package com.starry.sky.interfaces.facade;

import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
import com.starry.sky.interfaces.dto.Oauth2TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-27
 */

@RestController
@RefreshScope
public class AuthController {


    @Value(value = "${starry.sky.demo}")
    private String name;

    @Value(value = "${custom.app-name}")
    private String appName;

    @Autowired
    private RedissonLockTemplate redissonLockTemplate;


    @RequestMapping("hello")
    public String hello(Oauth2TokenDTO oauth2TokenDTO) {
        System.out.println(oauth2TokenDTO);


        return "hello \t" + name + "\t applocation:\t" + appName;
    }


}
