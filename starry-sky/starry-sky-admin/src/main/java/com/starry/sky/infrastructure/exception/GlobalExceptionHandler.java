package com.starry.sky.infrastructure.exception;

import com.starry.sky.common.exception.CustomizeRuntimeException;
import com.starry.sky.common.utils.ResultData;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wax
 * @description: 全局异常处理
 * @date 2021-08-20
 */
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


    @ExceptionHandler(CustomizeAuthenticationException.class)
    public ResultData customizeError(CustomizeAuthenticationException ex) {
        return ResultData.customizeResult(ex.getCode(), ex.getMessage());
    }

}
