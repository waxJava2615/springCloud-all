package com.starry.sky.domain.service.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.infrastructure.utils.ResultCode;
import com.starry.sky.infrastructure.utils.ResultData;
import com.starry.sky.infrastructure.exception.CustomizeAccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wax
 * @description: 权限不足自定
 * AccessDeineHandler 用来解决认证过的用户访问无权限资源时的异常
 * @date 2021-08-23
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {



    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 前后分离输出JSON   此处有全局异常捕捉
        ResultData resultData = null;
        if (accessDeniedException instanceof CustomizeAccessDeniedException) {
            resultData = ResultData.customizeResult(((CustomizeAccessDeniedException) accessDeniedException).getCode(),
                    accessDeniedException.getMessage());
        } else {
            resultData = ResultData.customizeResult(ResultCode.ACCESS_DENIED_NO_PERMISSION.getCode(),
                    ResultCode.ACCESS_DENIED_NO_PERMISSION.getMessage());
        }
        String jsonData = objectMapper.writeValueAsString(resultData);
        ResultData.printJson(request, response, jsonData);
    }
}
