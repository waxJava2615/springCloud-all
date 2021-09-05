package com.starry.sky.infrastructure.utils.cache.generate;

import lombok.Getter;

@Getter
public enum CacheKeyEnum {
    
    /**
     * 用户缓存   通过key获取map缓存  通过map缓存的KEY获取list列表
     */
    SYS_ADMIN_USER_LIST(CacheKeyConstants.SYS_ADMIN_USER_USER_LIST,"sysAdminUser","list","user"),
    /**
     * 对象缓存   通过key获取MAP缓存  能够通过map的KEY获取到具体user
     */
    SYS_ADMIN_USER_OBJ(CacheKeyConstants.SYS_ADMIN_USER_USER_OBJ,"sysAdminUser","obj","user"),
    
    SYS_ADMIN_USER_DEFAULT(CacheKeyConstants.SYS_ADMIN_USER_USER_DDEFAULT,"sysAdminUser","default",
            "user"),
    
    
    
    /**
     * 暂无用处   方便修改是不用添加;结尾
     */
    DEFAULT(0,"sysAdminDefault","default","default");
    private final Integer code;
    private final String prefix;
    private final String type;
    private final String group;
    
    CacheKeyEnum(Integer code, String prefix,String type,String group){
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
