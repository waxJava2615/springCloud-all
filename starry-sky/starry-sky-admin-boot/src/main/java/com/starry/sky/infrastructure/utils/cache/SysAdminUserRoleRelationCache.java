package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.dto.SysAdminUserRoleDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminUserRoleRelation;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wax
 * @description: 用户角色关联缓存
 * @date 2021-09-09
 */
@Component
public class SysAdminUserRoleRelationCache extends AbstractParamsCacheKey {


    /**
     * 缓存管理器    管理当前对象的单个KEY
     *
     * @return
     */
    @Override
    public String generateObjManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_DEFAULT));
    }


    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_LIST));
    }

    public String findByUserIdKey(SysAdminUserRoleDTO sysAdminUserRoleDTO) {
        return String.format("%s-findByUserId:%s", this.getClass().getSimpleName(), sysAdminUserRoleDTO.getUserId());
    }

    public List<SysAdminUserRoleRelation> findByUserId(SysAdminUserRoleDTO sysAdminUserRoleDTO) {
        String cacheKey = findByUserIdKey(sysAdminUserRoleDTO);
        return super.getList(cacheKey, SysAdminUserRoleRelation.class);
    }

    public void findByUserId(SysAdminUserRoleDTO sysAdminUserRoleDTO, List<SysAdminUserRoleRelation> list) {
        String cacheKey = findByUserIdKey(sysAdminUserRoleDTO);
        super.setList(cacheKey, list, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }
}
