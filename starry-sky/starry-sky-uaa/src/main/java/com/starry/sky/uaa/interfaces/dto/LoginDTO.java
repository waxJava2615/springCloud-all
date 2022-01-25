package com.starry.sky.uaa.interfaces.dto;

import lombok.Data;

/**
 * @author wax
 * @description: 登录DTO
 * @date 2021-10-28
 */
@Data
public class LoginDTO {

    /**
     * 登录方式   图形验证码    手机验证码 等等
     */
    private int loginType;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 图形验证码  手机验证码
     */
    private String code;

}
