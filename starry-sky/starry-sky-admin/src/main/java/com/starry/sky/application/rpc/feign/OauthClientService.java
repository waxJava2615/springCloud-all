package com.starry.sky.application.rpc.feign;

import com.starry.sky.infrastructure.utils.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author wax
 * @description: 授权接口请求
 * @date 2021-11-09
 */
@FeignClient("starry-sky-oauth2")
public interface OauthClientService {

    /**
     * 获取ACCESS_TOKEN
     * @param parameters
     * @return
     */
    @PostMapping(value = "/api/accessToken.do")
    ResultData getAccessToken(@RequestParam Map<String, String> parameters);

}
