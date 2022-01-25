package com.starry.sky.infrastructure.exceptions;

import com.starry.sky.infrastructure.utils.enums.IResultCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-29
 */
@Getter
public class CustomAuthenticationException extends AuthenticationException {

    private int code = -1;


    public CustomAuthenticationException(IResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public CustomAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomAuthenticationException(String message, int code) {
        super(message);
        this.code = code;
    }

    public CustomAuthenticationException(int code, String message) {
        super(message);
        this.code = code;
    }
}
