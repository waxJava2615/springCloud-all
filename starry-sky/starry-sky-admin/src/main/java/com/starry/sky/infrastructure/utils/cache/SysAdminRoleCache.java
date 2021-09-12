package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.annotation.CustomGenerateCacheKey;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.param.SysAdminRoleParam;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-10
 */
@Component
public class SysAdminRoleCache extends AbstractParamsCacheKey {

    @CustomGenerateCacheKey
    @Override
    public String generateList() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_LIST));
    }

    @Override
    public String generateObj() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_OBJ),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_OBJ),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_OBJ));
    }

    @Override
    public String generateDefault() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_DEFAULT));
    }

    /**
     * 是否支持改类型参数
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean support(Class clazz) {
        return SysAdminRoleParam.class.isAssignableFrom(clazz);
    }

    public String findListKey(SysAdminRoleParam sysAdminRoleParam){
        return String.format("findList:%s:%s",sysAdminRoleParam.getPageNo(),sysAdminRoleParam.getPageSize());
    }

    public List<SysAdminRole> findList(SysAdminRoleParam sysAdminRoleParam){
        String cacheKey = generateList();
        String mapKey = findListKey(sysAdminRoleParam);
        return super.getMapForList(cacheKey, mapKey, SysAdminRole.class);
    }

    public void findList(SysAdminRoleParam sysAdminRoleParam,List<SysAdminRole> list){
        String cacheKey = generateList();
        String mapKey = findListKey(sysAdminRoleParam);
        super.setMapForList(cacheKey, mapKey,list);
    }


    public String findByIdsKey(SysAdminRoleParam sysAdminRoleParam){
        return String.format("findByIds:%s",sysAdminRoleParam.getListRoleId().stream().map(Object::toString).collect(Collectors.joining(",")));
    }

    public List<SysAdminRole> findByIds(SysAdminRoleParam sysAdminRoleParam) {
        String cacheKey = generateList();
        String mapKey = findByIdsKey(sysAdminRoleParam);
        return super.getMapForList(cacheKey, mapKey, SysAdminRole.class);
    }
    public void findByIds(SysAdminRoleParam sysAdminRoleParam,List<SysAdminRole> list){
        String cacheKey = generateList();
        String mapKey = findByIdsKey(sysAdminRoleParam);
        super.setMapForList(cacheKey, mapKey,list);
    }
}
