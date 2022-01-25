package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.annotation.CustomGenerateCacheKey;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;
import com.starry.sky.infrastructure.utils.Pager;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.generate.CacheTableConstans;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import com.starry.sky.infrastructure.utils.cache.provider.CacheKeyManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wax
 * @description: 菜单缓存
 * @date 2021-09-13
 */
@Slf4j
@Component
public class SysAdminMenuCache extends AbstractParamsCacheKey {


    @Autowired
    CacheKeyManager cacheKeyManager;


    @CustomGenerateCacheKey(useTable = {CacheTableConstans.TABLE_SYS_ADMIN_MENU},type =
            CacheKeyConstants.SYS_TYPE_DEFAULT)
    @Override
    public String generateObjManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_MENU_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_MENU_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_MENU_DEFAULT));
    }

    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @CustomGenerateCacheKey(useTable = {CacheTableConstans.TABLE_SYS_ADMIN_MENU},type = CacheKeyConstants.SYS_TYPE_LIST)
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_MENU_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_MENU_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_MENU_LIST));
    }


    private String findByMenuIdListKey(SysAdminMenuDTO sysAdminMenuDTO) {
        return String.format("%s-findByMenuIdList:%s", this.getClass().getSimpleName(),
                sysAdminMenuDTO.getListMenuId().stream().map(Objects::toString).collect(Collectors.joining(",")));
    }

    public List<SysAdminMenu> findByMenuIdList(SysAdminMenuDTO sysAdminMenuDTO) {
        String cacheKey = findByMenuIdListKey(sysAdminMenuDTO);
        log.info("findByMenuIdList cache key : {}",cacheKey);
        return super.getList(cacheKey, SysAdminMenu.class);
    }

    public void findByMenuIdList(SysAdminMenuDTO sysAdminMenuDTO, List<SysAdminMenu> listMenu) {
        String cacheKey = findByMenuIdListKey(sysAdminMenuDTO);
        // 添加到缓存管理中
        log.info("findByMenuIdList cache key : {}",cacheKey);
        cacheKeyManager.pushListManager(generateListManager(),cacheKey);
        super.setList(cacheKey, listMenu, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }


    private String findByMenuIdKey(SysAdminMenuDTO sysAdminMenuDTO) {
        return String.format("%s-findByMenuId:%s", this.getClass().getSimpleName(), sysAdminMenuDTO.getId());
    }

    public SysAdminMenu findByMenuId(SysAdminMenuDTO sysAdminMenuDTO) {
        String cacheKey = findByMenuIdKey(sysAdminMenuDTO);
        log.info("findByMenuId cache key : {}",cacheKey);
        SysAdminMenu sysAdminMenu = super.get(cacheKey, SysAdminMenu.class);
        if (sysAdminMenu == null || sysAdminMenu.getId() == Long.MAX_VALUE) {
            return null;
        }
        return sysAdminMenu;
    }

    public void findByMenuId(SysAdminMenuDTO sysAdminMenuDTO, SysAdminMenu sysAdminMenu) {
        String cacheKey = findByMenuIdKey(sysAdminMenuDTO);
        log.info("findByMenuId cache key : {}",cacheKey);
        cacheKeyManager.pushObjManager(generateObjManager(),sysAdminMenuDTO.getId().toString(),cacheKey);
        super.set(cacheKey, sysAdminMenu, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }


    private String findListByHideKey(SysAdminMenuDTO sysAdminMenuDTO) {
        return String.format("%s-findListByHide:%s:%s:%s", this.getClass().getSimpleName(),
                sysAdminMenuDTO.getPageNo(),sysAdminMenuDTO.getPageSize(), sysAdminMenuDTO.getHide());
    }
    public List<SysAdminMenu> findListByHide(SysAdminMenuDTO sysAdminMenuDTO) {
        String cacheKey = findListByHideKey(sysAdminMenuDTO);
        log.info("findListByHide cache key : {}",cacheKey);
        return super.getList(cacheKey, SysAdminMenu.class);
    }

    public void findListByHide(SysAdminMenuDTO sysAdminMenuDTO, List<SysAdminMenu> sysAdminMenuTempList) {
        String cacheKey = findListByHideKey(sysAdminMenuDTO);
        log.info("findListByHide cache key : {}",cacheKey);
        cacheKeyManager.pushListManager(generateListManager(),cacheKey);
        super.setList(cacheKey, sysAdminMenuTempList, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }

    private String findMenuListKey(SysAdminMenuDTO sysAdminMenuDTO) {
        return String.format("%s-findMenuList:%s:%s:%s:%s:%s:%s", this.getClass().getSimpleName(),
                sysAdminMenuDTO.getPageNo(),sysAdminMenuDTO.getPageSize(), sysAdminMenuDTO.getHide(),
                sysAdminMenuDTO.getId(),sysAdminMenuDTO.getParentId(),sysAdminMenuDTO.getName());
    }

    public Pager<SysAdminMenu> findMenuList(SysAdminMenuDTO sysAdminMenuDTO) {
        String cacheKey = findMenuListKey(sysAdminMenuDTO);
        log.info("findMenuList cache key : {}",cacheKey);
        cacheKeyManager.pushListManager(generateListManager(),cacheKey);
        return super.get(cacheKey, Pager.class);

    }

    public void findMenuList(SysAdminMenuDTO sysAdminMenuDTO,Pager<SysAdminMenu> pager) {
        String cacheKey = findMenuListKey(sysAdminMenuDTO);
        log.info("findMenuList cache key : {}",cacheKey);
        super.set(cacheKey, pager,CommonConstants.SYS_DEFAULT_CACHE_TIME);

    }
    private String findByOnlyKeyKey(SysAdminMenuDTO sysAdminMenuDTO){
        return String.format("%s-findByOnlyKey:%s", this.getClass().getSimpleName(), sysAdminMenuDTO.getOnlyKey());
    }


    public SysAdminMenu findByOnlyKey(SysAdminMenuDTO sysAdminMenuDTO) {
        String cacheKey = findByOnlyKeyKey(sysAdminMenuDTO);
        log.info("findByOnlyKey cache key : {}",cacheKey);
        return super.get(cacheKey, SysAdminMenu.class);
    }

    public void findByOnlyKey(SysAdminMenuDTO sysAdminMenuDTO, SysAdminMenu sysAdminMenu) {
        String cacheKey = findByOnlyKeyKey(sysAdminMenuDTO);
        log.info("findByOnlyKey cache key : {}",cacheKey);
        cacheKeyManager.pushObjManager(generateObjManager(),sysAdminMenuDTO.getId().toString(),cacheKey);
        super.set(cacheKey, sysAdminMenu, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }
}
