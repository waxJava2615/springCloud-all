package com.starry.sky.domain.service.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.starry.sky.infrastructure.utils.ResultCode;
import com.starry.sky.infrastructure.utils.ResultData;
import com.starry.sky.domain.entity.AuthenticationUser;
import com.starry.sky.infrastructure.config.authentication.JwtGenerateProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author wax
 * @description: 自定义实现成功处理
 * @date 2021-08-23
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    private JwtGenerateProcess jwtGenerateProcess;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                        Authentication authentication) throws IOException, ServletException {
        onAuthenticationSuccess(request,response,authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        AuthenticationUser principal = (AuthenticationUser) authentication.getPrincipal();
        Map<String, Object> claims = Maps.newHashMap();
        // 放入一些用户信息,看具体情况
        claims.put(JwtGenerateProcess.CLAIMS_KEY_NAME_USER_NAME,principal.getUsername());
        // 生成token
        String jwtToken = jwtGenerateProcess.createJwtToken(claims);
        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("accessToken",jwtToken);
        ResultData resultData = new ResultData();
        resultData.setCode(ResultCode.SUCCESS.getCode());
        resultData.setMsg(ResultCode.SUCCESS.getMessage());
        resultData.setData(dataMap);
        // json返回前段
        String jsonData = objectMapper.writeValueAsString(resultData);
        ResultData.printJson(request,response,jsonData);
    }
}
