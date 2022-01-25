package com.starry.sky.infrastructure.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author wax
 * @description: 自定义异常
 * @date 2021-08-10
 */
public class CustomizeAuthenticationException extends AuthenticationException {

    private int code = -1;

    public CustomizeAuthenticationException(int code, String msg) {
        this(msg);
        this.code = code;
    }


    public CustomizeAuthenticationException(int code, String message, Throwable cause) {
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
     * Constructs an {@code AuthenticationException} with the specified message and root
     * cause.
     *
     * @param msg the detail message
     * @param t   the root cause
     */
    public CustomizeAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Constructs an {@code AuthenticationException} with the specified message and no
     * root cause.
     *
     * @param msg the detail message
     */
    public CustomizeAuthenticationException(String msg) {
        super(msg);
    }
}
