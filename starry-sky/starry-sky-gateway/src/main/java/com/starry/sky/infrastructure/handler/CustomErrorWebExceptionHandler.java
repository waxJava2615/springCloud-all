package com.starry.sky.infrastructure.handler;


import com.starry.sky.infrastructure.exception.CustomizeRuntimeException;
import com.starry.sky.infrastructure.utils.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常处理
 *
 * <p>异常时用JSON代替HTML异常信息<p>
 *
 * @author leftso
 *
 */
@Slf4j
public class CustomErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {


    public CustomErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /**
     * 获取异常属性
     */
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request,ErrorAttributeOptions options) {
        int code = -1;
        String msg = "未知错误";
        // 这里其实可以根据异常类型进行定制化逻辑
        Throwable error = super.getError(request);
        log.error("异常信息\t",error);
        //error instanceof NotFoundException
        if (error instanceof ResponseStatusException){
            ResponseStatusException err = (ResponseStatusException) error;
            if (err.getStatus() == HttpStatus.NOT_FOUND){
                code = ResultCode.REQUEST_URL_NOT_FOUND.getCode();
                msg = ResultCode.REQUEST_URL_NOT_FOUND.getMessage();
            }else if (err.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR || err.getStatus() == HttpStatus.SERVICE_UNAVAILABLE){
                code = ResultCode.REQUEST_SERVER_ERROR.getCode();
                msg = ResultCode.REQUEST_SERVER_ERROR.getMessage();
            }
        }else if (error instanceof CustomizeRuntimeException){
            CustomizeRuntimeException exception = (CustomizeRuntimeException) error;
            code = exception.getCode();
            msg = exception.getMessage();
        }else if (error instanceof RpcException){
            code = ResultCode.REQUEST_SERVER_ERROR.getCode();
            msg = ResultCode.REQUEST_SERVER_ERROR.getMessage();
        }
        return response(code, msg);
    }

    /**
     * 指定响应处理方法为JSON处理的方法
     * @param errorAttributes
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * 根据code获取对应的HttpStatus
     * @param errorAttributes
     * @return
     */
    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        //注意这里必须返回HTTP代码200否则无法显示错误信息
        return HttpStatus.OK.value();
    }

    /**
     * 构建异常信息
     * @param request
     * @param ex
     * @return
     */
    private String buildMessage(ServerRequest request, Throwable ex) {
        StringBuffer message = new StringBuffer("Failed to handle request [");
        message.append(request.methodName());
        message.append(" ");
        message.append(request.uri());
        message.append("]");
        if (ex != null) {
            message.append(": ");
            message.append(ex.getMessage());
        }
        return message.toString();
    }

    /**
     * 构建返回的JSON数据格式
     * @param status        状态码
     * @param errorMessage  异常信息
     * @return
     */
    public static Map<String, Object> response(int status, String errorMessage) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", status);
        map.put("msg", errorMessage);
        map.put("data", null);
        return map;
    }

}
