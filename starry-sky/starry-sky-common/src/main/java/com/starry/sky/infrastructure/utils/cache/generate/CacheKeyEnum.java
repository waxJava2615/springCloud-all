package com.starry.sky.infrastructure.utils.cache.generate;

import lombok.Getter;

/**
 * @author wax
 * @description: 缓存枚举
 * @date 2021-09-09 17:42
 */
@Getter
public enum CacheKeyEnum {


    OBJECT_DEFAULT(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_DEFAULT,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_DEFAULT, CacheTableConstans.TABLE_SYS_ADMIN_USER_ROLE_RELATION),
    OBJECT_LIST(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_LIST,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_LIST, CacheTableConstans.TABLE_SYS_ADMIN_USER_ROLE_RELATION),

    
    /**
     * 用户缓存   通过key获取map缓存  通过map缓存的KEY获取list列表
     */
    SYS_ADMIN_USER_LIST(CacheKeyConstants.SYS_ADMIN_USER_LIST, CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_LIST, CacheTableConstans.TABLE_SYS_ADMIN_USER),
    /**
     * 对象缓存   通过key获取MAP缓存  能够通过map的KEY获取到具体user
     */
    SYS_ADMIN_USER_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_USER_JOIN_TABLE, CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_JOINTABLE, CacheTableConstans.TABLE_SYS_ADMIN_USER),
    
    SYS_ADMIN_USER_DEFAULT(CacheKeyConstants.SYS_ADMIN_USER_DEFAULT, CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_DEFAULT,
            CacheTableConstans.TABLE_SYS_ADMIN_USER),


    SYS_ADMIN_USER_ROLE_RELATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_DEFAULT,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_DEFAULT, CacheTableConstans.TABLE_SYS_ADMIN_USER_ROLE_RELATION),
    SYS_ADMIN_USER_ROLE_RELATION_LIST(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_LIST,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_LIST, CacheTableConstans.TABLE_SYS_ADMIN_USER_ROLE_RELATION),
    SYS_ADMIN_USER_ROLE_RELATION_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_JOIN_TABLE,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_JOINTABLE, CacheTableConstans.TABLE_SYS_ADMIN_USER_ROLE_RELATION),
    
    
    SYS_ADMIN_ROLE_DEFAULT(CacheKeyConstants.SYS_ADMIN_ROLE_DEFAULT,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_DEFAULT, CacheTableConstans.TABLE_SYS_ADMIN_ROLE),
    SYS_ADMIN_ROLE_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_ROLE_JOIN_TABLE,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_JOINTABLE, CacheTableConstans.TABLE_SYS_ADMIN_ROLE),
    SYS_ADMIN_ROLE_LIST(CacheKeyConstants.SYS_ADMIN_ROLE_LIST,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_LIST, CacheTableConstans.TABLE_SYS_ADMIN_ROLE),
    

    
    SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_LIST, CacheTableConstans.TABLE_SYS_ADMIN_ROLE_PERMISSION_RELATION),
    SYS_ADMIN_ROLE_PERMISSION_RELATION_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_JOIN_TABLE,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_JOINTABLE, CacheTableConstans.TABLE_SYS_ADMIN_ROLE_PERMISSION_RELATION),
    SYS_ADMIN_ROLE_PERMISSION_RELATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_DEFAULT,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_DEFAULT, CacheTableConstans.TABLE_SYS_ADMIN_ROLE_PERMISSION_RELATION),

    SYS_ADMIN_PERMISSION_DEFAULT(CacheKeyConstants.SYS_ADMIN_PERMISSION_DEFAULT,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_DEFAULT, CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION),
    SYS_ADMIN_PERMISSION_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_PERMISSION_JOIN_TABLE,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_JOINTABLE, CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION),
    SYS_ADMIN_PERMISSION_LIST(CacheKeyConstants.SYS_ADMIN_PERMISSION_LIST,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_LIST, CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION),
    
    SYS_ADMIN_PERMISSION_MENU_RELATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_DEFAULT,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_DEFAULT, CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION_MENU_RELATION),
    SYS_ADMIN_PERMISSION_MENU_RELATION_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_JOIN_TABLE,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_JOINTABLE, CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION_MENU_RELATION),
    SYS_ADMIN_PERMISSION_MENU_RELATION_LIST(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_LIST,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_LIST, CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION_MENU_RELATION),
    
    SYS_ADMIN_PERMISSION_OPERATION_RELATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_DEFAULT,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_DEFAULT, CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION_OPERATION_RELATION),
    SYS_ADMIN_PERMISSION_OPERATION_RELATION_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_JOIN_TABLE,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_JOINTABLE, CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION_OPERATION_RELATION),
    SYS_ADMIN_PERMISSION_OPERATION_RELATION_LIST(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_LIST,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_LIST, CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION_OPERATION_RELATION),

    SYS_ADMIN_OPERATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_OPERATION_DEFAULT,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_DEFAULT, CacheTableConstans.TABLE_SYS_ADMIN_OPERATION),
    SYS_ADMIN_OPERATION_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_OPERATION_JOIN_TABLE,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_JOINTABLE, CacheTableConstans.TABLE_SYS_ADMIN_OPERATION),
    SYS_ADMIN_OPERATION_LIST(CacheKeyConstants.SYS_ADMIN_OPERATION_LIST,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_LIST, CacheTableConstans.TABLE_SYS_ADMIN_OPERATION),

    SYS_ADMIN_MENU_DEFAULT(CacheKeyConstants.SYS_ADMIN_MENU_DEFAULT,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_DEFAULT, CacheTableConstans.TABLE_SYS_ADMIN_MENU),
    SYS_ADMIN_MENU_LIST(CacheKeyConstants.SYS_ADMIN_MENU_LIST,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_LIST, CacheTableConstans.TABLE_SYS_ADMIN_MENU),
    SYS_ADMIN_MENU_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_MENU_JOIN_TABLE,
            CacheKeyConstants.SYS_ADMIN_PREFIX, CacheKeyConstants.SYS_TYPE_JOINTABLE, CacheTableConstans.TABLE_SYS_ADMIN_MENU),


    /**
     * 暂无用处   方便修改是不用添加;结尾
     */
    DEFAULT(0, "sysAdminDefault", CacheKeyConstants.SYS_TYPE_DEFAULT, CacheKeyConstants.SYS_TYPE_DEFAULT);
    private final Integer code;
    private final String prefix;
    private final String type;
    private final String group;
    
    CacheKeyEnum(Integer code, String prefix, String type, String group) {
        this.code = code;
        this.prefix = prefix;
        this.type = type;
        this.group = group;
    }
    
    /**
     * 根据code获去prefix
     *
     * @param code
     * @return
     */
    public static String getPrefixByCode(Integer code) {
        for (CacheKeyEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getPrefix();
            }
        }
        return null;
    }
    
    /**
     * 获取分组
     *
     * @param code
     * @return
     */
    public static String getGroupByCode(Integer code) {
        for (CacheKeyEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getGroup();
            }
        }
        return null;
    }
    
    /**
     * 获取类型
     *
     * @param code
     * @return
     */
    public static String getTypeByCode(Integer code) {
        for (CacheKeyEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getType();
            }
        }
        return null;
    }
    
}
