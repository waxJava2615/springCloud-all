package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.annotation.CustomGenerateCacheKey;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.dto.SysAdminOperationDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminOperation;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.generate.CacheTableConstans;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import com.starry.sky.infrastructure.utils.cache.provider.CacheKeyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wax
 * @description: 操作缓存
 * @date 2021-09-13
 */
@Component
public class SysAdminOperationCache extends AbstractParamsCacheKey {


    @Autowired
    CacheKeyManager cacheKeyManager;

    /**
     * 缓存管理器    管理当前对象的单个KEY
     *
     * @return
     */
    @CustomGenerateCacheKey(useTable = {CacheTableConstans.TABLE_SYS_ADMIN_OPERATION},type =
            CacheKeyConstants.SYS_TYPE_DEFAULT)
    @Override
    public String generateObjManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_OPERATION_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_OPERATION_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_OPERATION_DEFAULT));
    }

    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @CustomGenerateCacheKey(useTable = {CacheTableConstans.TABLE_SYS_ADMIN_OPERATION},type = CacheKeyConstants.SYS_TYPE_LIST)
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_OPERATION_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_OPERATION_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_OPERATION_LIST));
    }


    private String findByOptionIdKey(SysAdminOperationDTO sysAdminOperationDTO) {
        return String.format("%s-findByOptionId:%s",this.getClass().getSimpleName(),
                sysAdminOperationDTO.getListOperationId().stream().map(Objects::toString).collect(Collectors.joining(",")));
    }

    public List<SysAdminOperation> findByOptionId(SysAdminOperationDTO sysAdminOperationDTO) {
        String cacheKey = findByOptionIdKey(sysAdminOperationDTO);
        return super.getList(cacheKey, SysAdminOperation.class);
    }


    public void findByOptionId(SysAdminOperationDTO sysAdminOperationDTO, List<SysAdminOperation> listOperation) {
        String cacheKey = findByOptionIdKey(sysAdminOperationDTO);
        cacheKeyManager.pushListManager(generateListManager(),cacheKey);
        super.setList(cacheKey,listOperation, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }


    private String findListByHideKey(SysAdminOperationDTO sysAdminOperationDTO) {
        return String.format("%s-findListByHide:%s:%s:%s",this.getClass().getSimpleName(),
                sysAdminOperationDTO.getPageNo(),sysAdminOperationDTO.getPageSize(),sysAdminOperationDTO.getHide());
    }

    public List<SysAdminOperation> findListByHide(SysAdminOperationDTO sysAdminOperationDTO) {
        String cacheKey = findListByHideKey(sysAdminOperationDTO);
        return super.getList(cacheKey, SysAdminOperation.class);
    }

    public void findListByHide(SysAdminOperationDTO sysAdminOperationDTO, List<SysAdminOperation> listOperation) {
        String cacheKey = findListByHideKey(sysAdminOperationDTO);
        cacheKeyManager.pushListManager(generateListManager(),cacheKey);
        super.setList(cacheKey,listOperation, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }
}
