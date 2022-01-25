package com.starry.sky.infrastructure.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-07
 */
public class CommonConstants {


    public static final String CLIENT_ID_KEY = "client_id";

    public static final String AUTHORIZATION_KEY = "Authorization";


    public static final String BASIC_PREFIX = "Bearer";

    /**
     * 缓存时长
     */
    public static final long SYS_DEFAULT_CACHE_TIME = 2 * 60 * 60;

    public static final Charset UTF_8 = StandardCharsets.UTF_8;


    /**
     * 忽略某参数
     */
    public static final int IGNORE_FILED = -1;

    public static final int HIDE_YES = 1;
    public static final int HIDE_NO = 0;


    public static final int YES = 1;
    public static final int NO = 0;


    public static final int LEFT_MENU_OPTION = 0;
    public static final int TOP_MENU_OPTION = 1;

    public static final String DUBBO_SUB_STARRY_SKY_ADMIN = "starry-sky-admin";


    public static final String FIX_OBLIQUE = "/";

    /**
     * 权限操作后缀
     */
    public static final String ROLE_OPERATION_SUFFIX_PUSH = "/push.do";
    public static final String ROLE_OPERATION_SUFFIX_EDIT = "/edit.do";
    public static final String ROLE_OPERATION_SUFFIX_REMOVE = "/remove.do";
    public static final String ROLE_OPERATION_SUFFIX_EXPORT = "/export.do";

    public static final String ROLE_OPERATION_PUSH = "push";
    public static final String ROLE_OPERATION_EDIT = "edit";
    public static final String ROLE_OPERATION_REMOVE = "remove";
    public static final String ROLE_OPERATION_EXPORT = "export";


}
