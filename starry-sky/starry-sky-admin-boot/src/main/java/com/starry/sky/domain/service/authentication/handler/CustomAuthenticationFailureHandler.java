package com.starry.sky.domain.service.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.infrastructure.utils.ResultCode;
import com.starry.sky.infrastructure.utils.ResultData;
import com.starry.sky.infrastructure.exception.CustomizeAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wax
 * @description: 登录失败处理器
 * @date 2021-08-23
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Called when an authentication attempt fails.
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        ResultData resultData = null;
        if (exception instanceof CustomizeAuthenticationException){
            resultData = ResultData.customizeResult(((CustomizeAuthenticationException) exception).getCode(),
                    exception.getMessage());
        }else {
            resultData = ResultData.customizeResult(ResultCode.AUTHENTICATION_LOGIN_FAIL.getCode(),
                    ResultCode.AUTHENTICATION_LOGIN_FAIL.getMessage());
        }
        String jsonData = objectMapper.writeValueAsString(resultData);
        ResultData.printJson(request, response, jsonData);
    }
}
