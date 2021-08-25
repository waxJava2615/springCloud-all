package com.starry.sky.domain.service.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.common.utils.ResultCode;
import com.starry.sky.common.utils.ResultData;
import com.starry.sky.infrastructure.exception.CustomizeAuthenticationException;
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
 * @date 2021-08-23
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        CustomizeAuthenticationException customizeAuthenticationException =
                new CustomizeAuthenticationException(ResultCode.AUTHENTICATION_IDENTITY_FAIL.getCode(),
                        ResultCode.AUTHENTICATION_IDENTITY_FAIL.getMessage());
        if (authException != null && authException instanceof CustomizeAuthenticationException) {
            customizeAuthenticationException = (CustomizeAuthenticationException) authException;
        }

        // 身份验证失败,返回json
        ResultData resultData = ResultData.customizeResult(ResultCode.AUTHENTICATION_IDENTITY_FAIL.getCode(),
                ResultCode.AUTHENTICATION_IDENTITY_FAIL.getMessage());
        String jsonData = objectMapper.writeValueAsString(resultData);
        ResultData.printJson(request, response, jsonData);
    }
}
