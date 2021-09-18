package com.starry.sky.infrastructure.utils.cache.generate;

import lombok.Getter;

/**
 * @author wax
 * @description: 缓存枚举
 * @date 2021-09-09 17:42
 */
@Getter
public enum CacheKeyEnum {
    
    /**
     * 用户缓存   通过key获取map缓存  通过map缓存的KEY获取list列表
     */
    SYS_ADMIN_USER_LIST(CacheKeyConstants.SYS_ADMIN_USER_LIST, "sysAdmin", "list", "user"),
    /**
     * 对象缓存   通过key获取MAP缓存  能够通过map的KEY获取到具体user
     */
    SYS_ADMIN_USER_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_USER_JOIN_TABLE, "sysAdmin", "joinTable", "user"),
    
    SYS_ADMIN_USER_DEFAULT(CacheKeyConstants.SYS_ADMIN_USER_DEFAULT, "sysAdmin", "default",
            "user"),


    SYS_ADMIN_USER_ROLE_RELATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_DEFAULT,
            "sysAdmin", "default", "userRoleRelation"),
    SYS_ADMIN_USER_ROLE_RELATION_LIST(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_LIST,
            "sysAdmin", "list", "userRoleRelation"),
    SYS_ADMIN_USER_ROLE_RELATION_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_JOIN_TABLE,
            "sysAdmin", "joinTable", "userRoleRelation"),
    
    
    SYS_ADMIN_ROLE_DEFAULT(CacheKeyConstants.SYS_ADMIN_ROLE_DEFAULT,
            "sysAdmin", "default", "role"),
    SYS_ADMIN_ROLE_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_ROLE_JOIN_TABLE,
            "sysAdmin", "obj", "role"),
    SYS_ADMIN_ROLE_LIST(CacheKeyConstants.SYS_ADMIN_ROLE_LIST,
            "sysAdmin", "joinTable", "role"),
    

    
    SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST,
            "sysAdmin", "default", "rolePermissionRelation"),
    SYS_ADMIN_ROLE_PERMISSION_RELATION_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_JOIN_TABLE,
            "sysAdmin", "joinTable", "rolePermissionRelation"),
    SYS_ADMIN_ROLE_PERMISSION_RELATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST,
            "sysAdmin", "list", "rolePermissionRelation"),

    SYS_ADMIN_PERMISSION_DEFAULT(CacheKeyConstants.SYS_ADMIN_PERMISSION_DEFAULT,
            "sysAdmin", "default", "permission"),
    SYS_ADMIN_PERMISSION_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_PERMISSION_JOIN_TABLE,
            "sysAdmin", "joinTable", "permission"),
    SYS_ADMIN_PERMISSION_LIST(CacheKeyConstants.SYS_ADMIN_PERMISSION_LIST,
            "sysAdmin", "list", "permission"),
    
    SYS_ADMIN_PERMISSION_MENU_RELATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_DEFAULT,
            "sysAdmin", "default", "permissionMenuRelation"),
    SYS_ADMIN_PERMISSION_MENU_RELATION_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_JOIN_TABLE,
            "sysAdmin", "joinTable", "permissionMenuRelation"),
    SYS_ADMIN_PERMISSION_MENU_RELATION_LIST(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_LIST,
            "sysAdmin", "list", "permissionMenuRelation"),
    
    SYS_ADMIN_PERMISSION_OPERATION_RELATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_DEFAULT,
            "sysAdmin", "default", "permissionOperationRelation"),
    SYS_ADMIN_PERMISSION_OPERATION_RELATION_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_JOIN_TABLE,
            "sysAdmin", "joinTable", "permissionOperationRelation"),
    SYS_ADMIN_PERMISSION_OPERATION_RELATION_LIST(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_LIST,
            "sysAdmin", "list", "permissionOperationRelation"),

    SYS_ADMIN_OPERATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_OPERATION_DEFAULT,
            "sysAdmin", "default", "operation"),
    SYS_ADMIN_OPERATION_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_OPERATION_JOIN_TABLE,
            "sysAdmin", "joinTable", "operation"),
    SYS_ADMIN_OPERATION_LIST(CacheKeyConstants.SYS_ADMIN_OPERATION_LIST,
            "sysAdmin", "list", "operation"),

    SYS_ADMIN_MENU_DEFAULT(CacheKeyConstants.SYS_ADMIN_MENU_DEFAULT,
            "sysAdmin", "default", "menu"),
    SYS_ADMIN_MENU_LIST(CacheKeyConstants.SYS_ADMIN_MENU_LIST,
            "sysAdmin", "list", "menu"),
    SYS_ADMIN_MENU_JOIN_TABLE(CacheKeyConstants.SYS_ADMIN_MENU_JOIN_TABLE,
            "sysAdmin", "joinTable", "menu"),


    /**
     * 暂无用处   方便修改是不用添加;结尾
     */
    DEFAULT(0, "sysAdminDefault", "default", "default");
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
