package com.starry.sky.common.utils;

import lombok.Getter;

/**
 * @author wax
 * @description: 相应码枚举映射
 * @date 2021-08-20
 */
@Getter
public enum ResultCode {


    SUCCESS(0,"成功"),
    FAIL(10000,"失败"),


    AUTHENTICATION_SUPPORT_POST(20000,"只支持POST提交"),
    AUTHENTICATION_NOT_USER(20100,"用户不存在"),
    AUTHENTICATION_ERROR_PASSWORD(20150,"用户密码错误"),
    AUTHENTICATION_NO_PERMISSION(20200,"您没有访问权限"),
    AUTHENTICATION_IDENTITY_FAIL(20250,"身份认证失败"),
    AUTHENTICATION_LOGIN_FAIL(20300,"登录失败,请稍后再试"),



    fack(-9999,"");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
