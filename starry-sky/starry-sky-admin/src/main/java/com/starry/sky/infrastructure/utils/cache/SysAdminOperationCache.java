package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.dto.SysAdminOperationDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminOperation;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
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



    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_OPERATION_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_OPERATION_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_OPERATION_LIST));
    }

    /**
     * 缓存管理器   当前表连接的所有KEY
     *
     * @return
     */
    @Override
    public String generateJoinListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_OPERATION_JOIN_TABLE),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_OPERATION_JOIN_TABLE),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_OPERATION_JOIN_TABLE));
    }



    private String findByOptionIdKey(SysAdminOperationDTO sysAdminOperationDTO) {
        return String.format("findByOptionId:%s",
                sysAdminOperationDTO.getListOperationId().stream().map(Objects::toString).collect(Collectors.joining(",")));
    }

    public List<SysAdminOperation> findByOptionId(SysAdminOperationDTO sysAdminOperationDTO) {
        String cacheKey = findByOptionIdKey(sysAdminOperationDTO);
        return super.getList(cacheKey, SysAdminOperation.class);
    }


    public void findByOptionId(SysAdminOperationDTO sysAdminOperationDTO, List<SysAdminOperation> listOperation) {
        String cacheKey = findByOptionIdKey(sysAdminOperationDTO);
        super.setList(cacheKey,listOperation);
    }

}
