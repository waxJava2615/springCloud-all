package com.starry.sky.infrastructure.utils.constants;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-09
 */
public class OauthConstants {


    /**
     * 自定义token地址
     */
    public static final String CUSTOM_TOKEN_URL = "/api/accessToken.do";


    /**
     * 登录地址
     */
    public static String LOGIN_PRECESS_URL = "/api/login";


    /**
     * 登录请求提交方式
     */
    public static String LOGIN_PRECESS_HTTP_METHOD = "POST";



    public static String LONG_TYPE_CAPTCHA = "captcha";
    public static String LONG_TYPE_SMS = "sms";


    /**
     * 验证码缓存前缀
     */
    public static final String ADMIN_VALIDATE_CODE_PREFIX = "admin.validate.code.prefix";

    public static final String VIP_VALIDATE_CODE_PREFIX = "vip.validate.code.prefix";


}
