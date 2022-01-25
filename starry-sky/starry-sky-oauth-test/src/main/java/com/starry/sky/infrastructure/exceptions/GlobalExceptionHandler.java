package com.starry.sky.infrastructure.exceptions;

import com.starry.sky.infrastructure.exception.CustomizeRuntimeException;
import com.starry.sky.infrastructure.utils.ResultCode;
import com.starry.sky.infrastructure.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wax
 * @description: 全局异常处理
 * @date 2021-10-12
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


    @ExceptionHandler(value = OAuth2Exception.class)
    public ResultData handleOauth2(OAuth2Exception ex) {
        return ResultData.customizeResult(ResultCode.FAIL.getCode(), ex.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public ResultData customizeError(Exception ex) {
//        log.error("服务异常",ex);
//        if (ex instanceof NoHandlerFoundException) {
//            //404 Not Found  Target directory exists. Continue?
//            return ResultData.customizeResult(ResultCode.REQUEST_URL_NOT_FOUND.getCode(),ResultCode
//            .REQUEST_URL_NOT_FOUND.getMessage());
//        } else {
//            //500
//            return ResultData.customizeResult(ResultCode.REQUEST_SERVER_ERROR.getCode(),ResultCode
//            .REQUEST_SERVER_ERROR.getMessage());
//        }
//    }


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData unKnowExceptionHandler(Exception e) {
        e.printStackTrace();
        return this.serverErrorHandler();
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData runtimeExceptionHandler(RuntimeException ex) {
        ex.printStackTrace();
        return this.serverErrorHandler();
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData nullPointerExceptionHandler(Exception e) {

        e.printStackTrace();

        return this.serverErrorHandler();
    }

    /**
     * 类型转换异常
     */
    @ExceptionHandler(ClassCastException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData classCastExceptionHandler() {
        return this.serverErrorHandler();
    }

    /**
     * IO异常
     */
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData iOExceptionHandler() {
        return this.serverErrorHandler();
    }

    /**
     * 未知方法异常
     */
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData noSuchMethodExceptionHandler() {
        return this.serverErrorHandler();
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData indexOutOfBoundsExceptionHandler() {
        return this.serverErrorHandler();
    }

    /**
     * 400错误
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultData requestNotReadable() {
        return argumentsError();
    }

    /**
     * 400错误 类型不匹配
     */
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultData requestTypeMismatch() {
        return this.argumentsError();
    }

    /**
     * 400错误 缺少参数
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultData requestMissingServletRequest() {
        return this.argumentsError();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultData methodArgumentNotValidExceptionHandler() {
        return argumentsError();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultData usernameNotFound(UsernameNotFoundException ex) {
        return argumentsError();
    }


    /**
     * 405错误
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultData request405(HttpServletResponse resp) {
        return argumentsError();
    }


    /**
     * 500错误
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData server500(HttpServletResponse resp, Exception e) {
        return this.serverErrorHandler();
    }

    /**
     * 404
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultData notFoundException(HttpServletResponse response) {
        return ResultData.customizeResult(ResultCode.REQUEST_URL_NOT_FOUND.getCode(),
                ResultCode.REQUEST_URL_NOT_FOUND.getMessage());
    }

    @ExceptionHandler(value = ServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData serverErrorExceptionHandler(HttpServletResponse response) {
        return this.serverErrorHandler();
    }


    private ResultData serverErrorHandler() {
        return ResultData.customizeResult(ResultCode.REQUEST_SERVER_ERROR.getCode(),
                ResultCode.REQUEST_SERVER_ERROR.getMessage());
    }

    private ResultData argumentsError() {
        return ResultData.customizeResult(ResultCode.REQUEST_URL_NOT_FOUND.getCode(),
                ResultCode.REQUEST_URL_NOT_FOUND.getMessage());
    }


}
