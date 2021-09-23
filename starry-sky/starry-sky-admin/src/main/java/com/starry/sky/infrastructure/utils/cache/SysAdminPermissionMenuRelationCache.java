package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.dto.SysAdminPermissionMenuRelationDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionMenuRelation;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/11
 * @description 权限菜单
 */
@Component
public class SysAdminPermissionMenuRelationCache extends AbstractParamsCacheKey {

    /**
     * 缓存管理器    管理当前对象的单个KEY
     *
     * @return
     */
    @Override
    public String generateObjManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_DEFAULT));
    }

    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_LIST));
    }

    private String findByPermissionIdKey(SysAdminPermissionMenuRelationDTO sysAdminPermissionMenuRelationDTO) {
        return String.format("%s-findByPermissionId:%s",this.getClass().getSimpleName(),
                sysAdminPermissionMenuRelationDTO.getListOtherId().stream().map(Object::toString).collect(Collectors.joining(",")));
    }
    
    public List<SysAdminPermissionMenuRelation> findByPermissionId(SysAdminPermissionMenuRelationDTO sysAdminPermissionMenuRelationDTO) {
        String cacheKey = findByPermissionIdKey(sysAdminPermissionMenuRelationDTO);
        return super.getList(cacheKey, SysAdminPermissionMenuRelation.class);
    }
    
    public void findByPermissionId(SysAdminPermissionMenuRelationDTO sysAdminPermissionMenuRelationDTO, List<SysAdminPermissionMenuRelation> list) {
        String cacheKey = findByPermissionIdKey(sysAdminPermissionMenuRelationDTO);
        super.setList(cacheKey,list);
    }
}
