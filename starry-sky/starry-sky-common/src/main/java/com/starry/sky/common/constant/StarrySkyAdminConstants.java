package com.starry.sky.common.constant;

/**
 * @author wax
 * @description: admin后台常量管理
 * @date 2021-08-19
 */
public class StarrySkyAdminConstants {

    /**
     * 登录地址
     */
    public static String LOGIN_PRECESS_URL = "/admin/login";

    /**
     * 登录请求提交方式
     */
    public static String LOGIN_PRECESS_HTTP_METHOD = "POST";


    /*
     * 正常
     */
    public static int ACCOUNT_STATUS_DEFAULT = 0;
    /*
     * 过期
     */
    public static int ACCOUNT_STATUS_EXPIRED = 1;
    /*
     * 锁定
     */
    public static int ACCOUNT_STATUS_LOCK = 2;
    /*
     * 不可用
     */
    public static int ACCOUNT_STATUS_ENABLED = 3;
    
    /**
     * 缓存时长
     */
    public static long SYS_DEFAULT_CACHE_TIME = 2 * 60 * 60;


    

}
