package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.annotation.CustomGenerateCacheKey;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.generate.CacheTableConstans;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import com.starry.sky.infrastructure.utils.cache.provider.CacheKeyManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-10
 */
@Slf4j
@Component
public class SysAdminRoleCache extends AbstractParamsCacheKey {

    @Autowired
    CacheKeyManager cacheKeyManager;

    /**
     * 缓存管理器    管理当前对象的单个KEY
     *
     * @return
     */
    @CustomGenerateCacheKey(useTable = {CacheTableConstans.TABLE_SYS_ADMIN_ROLE},type = CacheKeyConstants.SYS_TYPE_DEFAULT)
    @Override
    public String generateObjManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_DEFAULT));
    }

    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @CustomGenerateCacheKey(useTable = {CacheTableConstans.TABLE_SYS_ADMIN_ROLE},type = CacheKeyConstants.SYS_TYPE_LIST)
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_LIST));
    }

    /**
     * 连表查询唯一标识
     * @return
     */
    @CustomGenerateCacheKey(useTable = {CacheTableConstans.TABLE_SYS_ADMIN_ROLE,CacheTableConstans.TABLE_SYS_ADMIN_ROLE_PERMISSION_RELATION,CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION,CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION_MENU_RELATION,CacheTableConstans.TABLE_SYS_ADMIN_MENU}, type = CacheKeyConstants.SYS_TYPE_JOINTABLE)
    private String joinRolePermissionMenuOnlyKey(){
        return this.generateJoinKey(CacheTableConstans.TABLE_SYS_ADMIN_ROLE,CacheTableConstans.TABLE_SYS_ADMIN_ROLE_PERMISSION_RELATION,CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION,CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION_MENU_RELATION,CacheTableConstans.TABLE_SYS_ADMIN_MENU);
    }

    @CustomGenerateCacheKey(useTable = {CacheTableConstans.TABLE_SYS_ADMIN_ROLE,
            CacheTableConstans.TABLE_SYS_ADMIN_ROLE_PERMISSION_RELATION,CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION
            ,CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION_OPERATION_RELATION,CacheTableConstans.TABLE_SYS_ADMIN_OPERATION},
            type = CacheKeyConstants.SYS_TYPE_JOINTABLE)
    private String joinRolePermissionOperationOnlyKey(){
        return this.generateJoinKey(CacheTableConstans.TABLE_SYS_ADMIN_ROLE,CacheTableConstans.TABLE_SYS_ADMIN_ROLE_PERMISSION_RELATION,CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION,CacheTableConstans.TABLE_SYS_ADMIN_PERMISSION_OPERATION_RELATION,CacheTableConstans.TABLE_SYS_ADMIN_OPERATION);
    }

    public String findListKey(SysAdminRoleDTO sysAdminRoleDTO) {
        return String.format("%s-findList:%s:%s", this.getClass().getSimpleName(),sysAdminRoleDTO.getPageNo(), sysAdminRoleDTO.getPageSize());
    }

    public List<SysAdminRole> findList(SysAdminRoleDTO sysAdminRoleDTO) {
        String cacheKey = findListKey(sysAdminRoleDTO);
        return super.getList(cacheKey, SysAdminRole.class);
    }

    public void findList(SysAdminRoleDTO sysAdminRoleDTO, List<SysAdminRole> list) {
        String cacheKey = findListKey(sysAdminRoleDTO);
        cacheKeyManager.pushListManager(generateListManager(),cacheKey);
        super.setList(cacheKey, list, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }


    public String findByIdsKey(SysAdminRoleDTO sysAdminRoleDTO) {
        return String.format("%s-findByIds:%s",this.getClass().getSimpleName(),
                sysAdminRoleDTO.getListRoleId().stream().map(Object::toString).collect(Collectors.joining(",")));
    }

    public List<SysAdminRole> findByIds(SysAdminRoleDTO sysAdminRoleDTO) {
        String cacheKey = findByIdsKey(sysAdminRoleDTO);
        return super.getList(cacheKey, SysAdminRole.class);
    }

    public void findByIds(SysAdminRoleDTO sysAdminRoleDTO, List<SysAdminRole> list) {
        String cacheKey = findByIdsKey(sysAdminRoleDTO);
        cacheKeyManager.pushListManager(generateListManager(),cacheKey);
        super.setList(cacheKey, list, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }


    private String findRolePermissionMenuKey(SysAdminRoleDTO sysAdminRoleDTO) {
        StringBuffer sb = new StringBuffer();
        String ridStr = "";
        if (sysAdminRoleDTO.getListRoleId() != null) {
            ridStr = StringUtils.join(sysAdminRoleDTO.getListRoleId(), ",");
        }
        sb.append(this.getClass().getSimpleName()).append("-").append("findRolePermissionMenu").append(":")
                .append(ridStr)
                .append(":")
                .append(sysAdminRoleDTO.getPageNo()).append(":").append(sysAdminRoleDTO.getPageSize())
                .append(":").append(sysAdminRoleDTO.getHide());
        return sb.toString();
    }

    public List<SysAdminRole> findRolePermissionMenu(SysAdminRoleDTO sysAdminRoleDTO) {
        String cacheKey = findRolePermissionMenuKey(sysAdminRoleDTO);
        log.info("findRolePermissionMenu cache key: {}",cacheKey);
        return super.getList(cacheKey, SysAdminRole.class);
    }

    public void findRolePermissionMenu(SysAdminRoleDTO sysAdminRoleDTO,
                                       List<SysAdminRole> list) {
        String cacheKey = findRolePermissionMenuKey(sysAdminRoleDTO);
        log.info("findRolePermissionMenu cache key: {}",cacheKey);
        cacheKeyManager.pushJoinTableManager(joinRolePermissionMenuOnlyKey(),cacheKey);
        super.setList(cacheKey, list, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }

    private String findRolePermissionOperationKey(SysAdminRoleDTO sysAdminRoleDTO) {
        StringBuffer sb = new StringBuffer();
        String ridStr = "";
        if (sysAdminRoleDTO.getListRoleId() != null) {
            ridStr = StringUtils.join(sysAdminRoleDTO.getListRoleId(), ",");
        }
        sb.append(this.getClass().getSimpleName()).append("-").append("findRolePermissionOperation").append(":")
                .append(ridStr)
                .append(":")
                .append(sysAdminRoleDTO.getPageNo()).append(":").append(sysAdminRoleDTO.getPageSize())
                .append(":").append(sysAdminRoleDTO.getHide());
        return sb.toString();
    }

    public List<SysAdminRole> findRolePermissionOperation(SysAdminRoleDTO sysAdminRoleDTO) {
        String cacheKey = findRolePermissionOperationKey(sysAdminRoleDTO);
        log.info("findRolePermissionOperation cache key: {}",cacheKey);
        return super.getList(cacheKey, SysAdminRole.class);
    }

    public void findRolePermissionOperation(SysAdminRoleDTO sysAdminRoleDTO, List<SysAdminRole> list) {
        String cacheKey = findRolePermissionOperationKey(sysAdminRoleDTO);
        log.info("findRolePermissionOperation cache key: {}",cacheKey);
        cacheKeyManager.pushJoinTableManager(joinRolePermissionOperationOnlyKey(),cacheKey);
        super.setList(cacheKey, list, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }


    private String findByNamesKey(SysAdminRoleDTO sysAdminRoleDTO) {
        StringBuffer sb = new StringBuffer();
        String ridStr = "";
        if (sysAdminRoleDTO.getListNames() != null) {
            ridStr = StringUtils.join(sysAdminRoleDTO.getListNames(), ",");
        }
        sb.append(this.getClass().getSimpleName()).append("-").append("findByNames").append(":")
                .append(ridStr)
                .append(":")
                .append(sysAdminRoleDTO.getPageNo()).append(":").append(sysAdminRoleDTO.getPageSize())
                .append(":").append(sysAdminRoleDTO.getHide());
        return sb.toString();
    }


    public List<SysAdminRole> findByNames(SysAdminRoleDTO sysAdminRoleDTO) {
        String cacheKey = findByNamesKey(sysAdminRoleDTO);
        log.info("findByNames cache key: {}",cacheKey);
        return super.getList(cacheKey, SysAdminRole.class);
    }

    public void findByNames(SysAdminRoleDTO sysAdminRoleDTO, List<SysAdminRole> list) {
        String cacheKey = findByNamesKey(sysAdminRoleDTO);
        log.info("findByNames cache key: {}",cacheKey);
        cacheKeyManager.pushListManager(generateListManager(),cacheKey);
        super.setList(cacheKey, list, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }

    private String findRoleByOperationIdKey(SysAdminRoleDTO sysAdminRoleDTO) {
        StringBuffer sb = new StringBuffer();
        String ridStr = "";
        if (sysAdminRoleDTO.getListOperationId() != null) {
            ridStr = StringUtils.join(sysAdminRoleDTO.getListOperationId(), ",");
        }
        sb.append(this.getClass().getSimpleName()).append("-").append("findRoleByOperationId").append(":")
                .append(ridStr)
                .append(":")
                .append(sysAdminRoleDTO.getPageNo()).append(":").append(sysAdminRoleDTO.getPageSize())
                .append(":").append(sysAdminRoleDTO.getHide());
        return sb.toString();
    }


    public List<SysAdminRole> findRoleByOperationId(SysAdminRoleDTO sysAdminRoleDTO) {
        String cacheKey = findRoleByOperationIdKey(sysAdminRoleDTO);
        log.info("findRoleByOperationId cache key: {}",cacheKey);
        return super.getList(cacheKey, SysAdminRole.class);
    }

    public void findRoleByOperationId(SysAdminRoleDTO sysAdminRoleDTO, List<SysAdminRole> list) {
        String cacheKey = findRoleByOperationIdKey(sysAdminRoleDTO);
        log.info("findRoleByOperationId cache key: {}",cacheKey);
        cacheKeyManager.pushJoinTableManager(joinRolePermissionOperationOnlyKey(),cacheKey);
        super.setList(cacheKey, list, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }

}
