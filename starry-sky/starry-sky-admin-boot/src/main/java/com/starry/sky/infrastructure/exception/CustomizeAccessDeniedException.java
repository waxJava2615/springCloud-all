package com.starry.sky.infrastructure.exception;

import org.springframework.security.access.AccessDeniedException;

/**
 * @author wax
 * @description: 权限异常处理
 * @date 2021-08-25
 */
public class CustomizeAccessDeniedException extends AccessDeniedException {


    private int code = -1;

    public CustomizeAccessDeniedException(int code, String msg) {
        this(msg);
        this.code = code;
    }


    public CustomizeAccessDeniedException(int code, String message, Throwable cause) {
        this(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    /**
     * Constructs an <code>AccessDeniedException</code> with the specified message.
     *
     * @param msg the detail message
     */
    public CustomizeAccessDeniedException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>AccessDeniedException</code> with the specified message and
     * root cause.
     *
     * @param msg the detail message
     * @param t   root cause
     */
    public CustomizeAccessDeniedException(String msg, Throwable t) {
        super(msg, t);
    }
}
