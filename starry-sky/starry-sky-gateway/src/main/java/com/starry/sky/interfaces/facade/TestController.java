package com.starry.sky.interfaces.facade;

import com.starry.sky.infrastructure.config.properties.ResourceServerProperties;
import com.starry.sky.infrastructure.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-18
 */
@RefreshScope
@Slf4j
@RestController
public class TestController {

    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @RequestMapping("/test.do")
    public ResultData testDate(){
        log.info("resourceServerProperties is null? {}",resourceServerProperties == null);
        log.info("resourceServerProperties IgnoreUrls value {}",resourceServerProperties.getIgnoreUrls());
        return ResultData.successResult();
    }


}
