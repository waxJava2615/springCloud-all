package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.annotation.CustomGenerateCacheKey;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.dto.SysAdminPermissionOperationRelationDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionOperationRelation;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.generate.CacheTableConstans;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import com.starry.sky.infrastructure.utils.cache.provider.CacheKeyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/11
 * @description 权限操作缓存
 */
@Component
public class SysAdminPermissionOperationRelationCache extends AbstractParamsCacheKey {


    @Autowired
    CacheKeyManager cacheKeyManager;

    /**
     * 缓存管理器    管理当前对象的单个KEY
     *
     * @return
     */
    @CustomGenerateCacheKey(useTable = {CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION_OPERATION_RELATION},type =
            CacheKeyConstants.SYS_TYPE_DEFAULT)
    @Override
    public String generateObjManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_DEFAULT));
    }

    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @CustomGenerateCacheKey(useTable = {CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION_OPERATION_RELATION},type =
            CacheKeyConstants.SYS_TYPE_LIST)
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_LIST));
    }


    private String findByPermissionIdKey(SysAdminPermissionOperationRelationDTO sysAdminPermissionOperationRelationDTO) {
        return String.format("%s-findByPermissionId:%s",this.getClass().getSimpleName(),
                sysAdminPermissionOperationRelationDTO.getListOtherId().stream().map(Object::toString).collect(Collectors.joining(",")));
    }
    
    public List<SysAdminPermissionOperationRelation> findByPermissionId(SysAdminPermissionOperationRelationDTO sysAdminPermissionOperationRelationDTO) {
        String cacheKey = findByPermissionIdKey(sysAdminPermissionOperationRelationDTO);
        return super.getList(cacheKey, SysAdminPermissionOperationRelation.class);
    }
    
    
    public void findByPermissionId(SysAdminPermissionOperationRelationDTO sysAdminPermissionOperationRelationDTO,
                                   List<SysAdminPermissionOperationRelation> list) {
        String cacheKey = findByPermissionIdKey(sysAdminPermissionOperationRelationDTO);
        cacheKeyManager.pushListManager(generateListManager(),cacheKey);
        super.setList(cacheKey, list, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }
}
