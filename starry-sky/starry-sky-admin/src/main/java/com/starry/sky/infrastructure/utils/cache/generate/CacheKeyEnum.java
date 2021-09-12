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
    SYS_ADMIN_USER_LIST(CacheKeyConstants.SYS_ADMIN_USER_LIST, "sysAdminUser", "list", "user"),
    /**
     * 对象缓存   通过key获取MAP缓存  能够通过map的KEY获取到具体user
     */
    SYS_ADMIN_USER_OBJ(CacheKeyConstants.SYS_ADMIN_USER_OBJ, "sysAdminUser", "obj", "user"),
    
    SYS_ADMIN_USER_DEFAULT(CacheKeyConstants.SYS_ADMIN_USER_DEFAULT, "sysAdminUser", "default",
            "user"),
    
    
    SYS_ADMIN_ROLE_DEFAULT(CacheKeyConstants.SYS_ADMIN_ROLE_DEFAULT,
            "sysAdminRole", "default", "role"),
    SYS_ADMIN_ROLE_OBJ(CacheKeyConstants.SYS_ADMIN_ROLE_OBJ,
            "sysAdminRole", "obj", "role"),
    SYS_ADMIN_ROLE_LIST(CacheKeyConstants.SYS_ADMIN_ROLE_LIST,
            "sysAdminRole", "list", "role"),
    
    
    SYS_ADMIN_USER_ROLE_RELATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_DEFAULT,
            "sysAdminUserRoleRelation", "default", "userRoleRelation"),
    SYS_ADMIN_USER_ROLE_RELATION_LIST(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_LIST,
            "sysAdminUserRoleRelation", "list", "userRoleRelation"),
    SYS_ADMIN_USER_ROLE_RELATION_OBJ(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_OBJ,
            "sysAdminUserRoleRelation", "obj", "userRoleRelation"),
    
    
    SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST,
            "sysAdminRolePermissionRelation", "default", "rolePermissionRelation"),
    SYS_ADMIN_ROLE_PERMISSION_RELATION_OBJ(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_OBJ,
            "sysAdminRolePermissionRelation", "obj", "rolePermissionRelation"),
    SYS_ADMIN_ROLE_PERMISSION_RELATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST,
            "sysAdminRolePermissionRelation", "list", "rolePermissionRelation"),
    
    
    
    SYS_ADMIN_PERMISSION_MENU_RELATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_DEFAULT,
            "sysAdminPermissionMenuRelation", "default", "permissionMenuRelation"),
    SYS_ADMIN_PERMISSION_MENU_RELATION_OBJ(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_OBJ,
            "sysAdminPermissionMenuRelation", "obj", "permissionMenuRelation"),
    SYS_ADMIN_PERMISSION_MENU_RELATION_LIST(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_LIST,
            "sysAdminPermissionMenuRelation", "list", "permissionMenuRelation"),
    
    SYS_ADMIN_PERMISSION_OPTION_RELATION_DEFAULT(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_DEFAULT,
            "sysAdminPermissionOptionRelation", "default", "permissionOptionRelation"),
    SYS_ADMIN_PERMISSION_OPTION_RELATION_OBJ(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_OBJ,
            "sysAdminPermissionOptionRelation", "obj", "permissionOptionRelation"),
    SYS_ADMIN_PERMISSION_OPTION_RELATION_LIST(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_LIST,
            "sysAdminPermissionOptionRelation", "list", "permissionOptionRelation"),
    
    
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
