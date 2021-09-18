package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import org.apache.commons.lang3.StringUtils;
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


    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_LIST));
    }

    /**
     * 缓存管理器   当前表连接的所有KEY
     *
     * @return
     */
    @Override
    public String generateJoinListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_JOIN_TABLE),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_JOIN_TABLE),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_JOIN_TABLE));
    }


    public String findListKey(SysAdminRoleDTO sysAdminRoleDTO) {
        return String.format("findList:%s:%s", sysAdminRoleDTO.getPageNo(), sysAdminRoleDTO.getPageSize());
    }

    public List<SysAdminRole> findList(SysAdminRoleDTO sysAdminRoleDTO) {
        String cacheKey = findListKey(sysAdminRoleDTO);
        return super.getList(cacheKey, SysAdminRole.class);
    }

    public void findList(SysAdminRoleDTO sysAdminRoleDTO, List<SysAdminRole> list) {
        String cacheKey = findListKey(sysAdminRoleDTO);
        super.setList(cacheKey, list);
    }


    public String findByIdsKey(SysAdminRoleDTO sysAdminRoleDTO) {
        return String.format("findByIds:%s",
                sysAdminRoleDTO.getListRoleId().stream().map(Object::toString).collect(Collectors.joining(",")));
    }

    public List<SysAdminRole> findByIds(SysAdminRoleDTO sysAdminRoleDTO) {
        String cacheKey = findByIdsKey(sysAdminRoleDTO);
        return super.getList(cacheKey, SysAdminRole.class);
    }

    public void findByIds(SysAdminRoleDTO sysAdminRoleDTO, List<SysAdminRole> list) {
        String cacheKey = findByIdsKey(sysAdminRoleDTO);
        super.setList(cacheKey, list);
    }


//    private String generateJoinRolePermissionMenuCacheKey() {
//        return generateJoinKey(CacheJoinConstans.TABLE_SYS_ADMIN_ROLE,
//                CacheJoinConstans.TABLE_SYS_ADMIN_ROLE_PERMISSION_RELATION,
//                CacheJoinConstans.TABLE_SYS_ADMIN_PERMISSION,
//                CacheJoinConstans.TABLE_SYS_ADMIN_PERMISSION_MENU_RELATION, CacheJoinConstans.TABLE_SYS_ADMIN_MENU);
//    }

    private String findRolePermissionMenuKey(SysAdminRoleDTO sysAdminRoleDTO) {
        StringBuffer sb = new StringBuffer();
        String ridStr = "findRolePermissionMenu:";
        if (sysAdminRoleDTO.getListRoleId() != null) {
            ridStr = StringUtils.join(sysAdminRoleDTO.getListRoleId(), ",");
        }
        sb.append(ridStr).append(":").append(sysAdminRoleDTO.getPageNo()).append(":").append(sysAdminRoleDTO.getPageSize()).append(":").append(sysAdminRoleDTO.getHide());
        return sb.toString();
    }

    public List<SysAdminRole> findRolePermissionMenu(SysAdminRoleDTO sysAdminRoleDTO) {
        String cacheKey = findRolePermissionMenuKey(sysAdminRoleDTO);
        return super.getList(cacheKey, SysAdminRole.class);
    }

    public void findRolePermissionMenu(SysAdminRoleDTO sysAdminRoleDTO,
                                       List<SysAdminRole> list) {
        String cacheKey = findRolePermissionMenuKey(sysAdminRoleDTO);
        super.setList(cacheKey, list);
    }
}
