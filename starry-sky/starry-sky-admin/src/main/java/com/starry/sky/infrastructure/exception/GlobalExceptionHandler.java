package com.starry.sky.infrastructure.exception;

import com.starry.sky.common.exception.CustomizeRuntimeException;
import com.starry.sky.common.utils.ResultCode;
import com.starry.sky.common.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wax
 * @description: 全局异常处理
 * @date 2021-08-20
 */
@Slf4j
@RestControllerAdvice
@Component
public class GlobalExceptionHandler {


    /**
     * @author wax
     * @description: 系统自定义异常抛出捕捉
     * @date 2021-07-20 16:01
     */
    @ExceptionHandler(CustomizeRuntimeException.class)
    public ResultData customizeError(CustomizeRuntimeException ex) {
        return ResultData.customizeResult(ex.getCode(), ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResultData customizeError(Exception ex) {
        log.error("服务异常",ex);
        if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            //404 Not Found  Target directory exists. Continue?
            return ResultData.customizeResult(ResultCode.REQUEST_URL_NOT_FOUND.getCode(),ResultCode.REQUEST_URL_NOT_FOUND.getMessage());
        } else {
            //500
            return ResultData.customizeResult(ResultCode.REQUEST_SERVER_ERROR.getCode(),ResultCode.REQUEST_SERVER_ERROR.getMessage());
        }
    }

    @ExceptionHandler(CustomizeAuthenticationException.class)
    public ResultData customizeError(CustomizeAuthenticationException ex) {
        return ResultData.customizeResult(ex.getCode(), ex.getMessage());
    }

}
