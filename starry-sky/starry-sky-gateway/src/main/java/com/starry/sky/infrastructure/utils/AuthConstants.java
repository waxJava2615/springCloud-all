package com.starry.sky.infrastructure.utils;

/**
 * @author wax
 * @description: 权限认证常量
 * @date 2021-11-01
 */
public class AuthConstants {


    /**
     * 权限集合
     */
    public static final String JWT_AUTHORITIES_KEY = "authorities";

    /**
     * 权限前缀
     */
    public static final String AUTHORITY_PREFIX = "";

    /**
     * 请求头
     */
    public static final String JWT_TOKEN_HEADER = "Authorization";

    /**
     * 获取token
     */
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * TOKEN载体
     */
    public static final String JWT_TOKEN_PAYLOAD = "payload";


    /**
     * APP
     */
    public static final String APP_API_PREFIX = "/*/app-api/**";

    /**
     * 管理端
     */
    public static final String ADMIN_URL_PATTERN = "/admin/**";

    /**
     * 超级管理员标识
     */
    public static final String ROOT_ROLE_CODE = "admin";


}
