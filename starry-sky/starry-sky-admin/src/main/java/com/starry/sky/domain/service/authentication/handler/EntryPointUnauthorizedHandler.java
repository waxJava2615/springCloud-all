package com.starry.sky.domain.service.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.common.utils.ResultCode;
import com.starry.sky.common.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wax
 * @description: 身份验证失败, 返回json
 *  AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 * @date 2021-08-23
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // 身份验证失败,返回json
        ResultData resultData = ResultData.customizeResult(ResultCode.AUTHENTICATION_IDENTITY_FAIL.getCode(),
                ResultCode.AUTHENTICATION_IDENTITY_FAIL.getMessage());
        String jsonData = objectMapper.writeValueAsString(resultData);
        ResultData.printJson(request, response, jsonData);
    }
}
