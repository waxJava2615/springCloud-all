package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wax
 * @description: 菜单缓存
 * @date 2021-09-13
 */
@Component
public class SysAdminMenuCache extends AbstractParamsCacheKey {


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
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_MENU_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_MENU_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_MENU_LIST));
    }


    private String findByMenuIdListKey(SysAdminMenuDTO sysAdminMenuDTO) {
        return String.format("%s-findByMenuIdList:%s",this.getClass().getSimpleName(),
                sysAdminMenuDTO.getListMenuId().stream().map(Objects::toString).collect(Collectors.joining(",")));
    }

    public List<SysAdminMenu> findByMenuIdList(SysAdminMenuDTO sysAdminMenuDTO) {
        String cacheKey = findByMenuIdListKey(sysAdminMenuDTO);
        return super.getList(cacheKey,SysAdminMenu.class);
    }

    public void findByMenuIdList(SysAdminMenuDTO sysAdminMenuDTO, List<SysAdminMenu> listMenu) {
        String cacheKey = findByMenuIdListKey(sysAdminMenuDTO);
        // 添加到缓存管理中

        super.setList(cacheKey,listMenu);
    }


    private String findByMenuIdKey(SysAdminMenuDTO sysAdminMenuDTO) {
        return String.format("$s-findByMenuId:%s",this.getClass().getSimpleName(),sysAdminMenuDTO.getId());
    }

    public SysAdminMenu findByMenuId(SysAdminMenuDTO sysAdminMenuDTO) {
        String cacheKey = findByMenuIdKey(sysAdminMenuDTO);
        SysAdminMenu sysAdminMenu = super.get(cacheKey, SysAdminMenu.class);
        if (sysAdminMenu == null || sysAdminMenu.getId() == Long.MAX_VALUE){
            return null;
        }
        return sysAdminMenu;
    }

    public void findByMenuId(SysAdminMenuDTO sysAdminMenuDTO, SysAdminMenu sysAdminMenu) {
        String cacheKey = findByMenuIdKey(sysAdminMenuDTO);
        super.set(cacheKey, sysAdminMenu);
    }
}
