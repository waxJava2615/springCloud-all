package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.dto.SysAdminPermissionOperationRelationDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionOperationRelation;
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
 * @description 权限操作缓存
 */
@Component
public class SysAdminPermissionOperationRelationCache extends AbstractParamsCacheKey {


    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_LIST));
    }

    /**
     * 缓存管理器   当前表连接的所有KEY
     *
     * @return
     */
    @Override
    public String generateJoinListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_JOIN_TABLE),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_JOIN_TABLE),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPERATION_RELATION_JOIN_TABLE));
    }

    private String findByPermissionIdKey(SysAdminPermissionOperationRelationDTO sysAdminPermissionOperationRelationDTO) {
        return String.format("findByPermissionId:%s",
                sysAdminPermissionOperationRelationDTO.getListOtherId().stream().map(Object::toString).collect(Collectors.joining(",")));
    }
    
    public List<SysAdminPermissionOperationRelation> findByPermissionId(SysAdminPermissionOperationRelationDTO sysAdminPermissionOperationRelationDTO) {
        String cacheKey = findByPermissionIdKey(sysAdminPermissionOperationRelationDTO);
        return super.getList(cacheKey, SysAdminPermissionOperationRelation.class);
    }
    
    
    public void findByPermissionId(SysAdminPermissionOperationRelationDTO sysAdminPermissionOperationRelationDTO,
                                   List<SysAdminPermissionOperationRelation> list) {
        String cacheKey = findByPermissionIdKey(sysAdminPermissionOperationRelationDTO);
        super.setList(cacheKey, list);
    }
}
