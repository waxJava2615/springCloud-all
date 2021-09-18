package com.starry.sky.infrastructure.utils.cache.provider;

import com.starry.sky.infrastructure.dto.SysAdminDTO;

import java.util.List;

/**
 * @author wax
 * @description: 缓存KEY的支持类型
 * @date 2021-09-09
 */
public interface CacheKey {

    /**
     * 获取所有的key
     * @param sysAdminDTO
     * @return
     * @throws Exception
     */
    public List<String> getCacheKeyList(SysAdminDTO sysAdminDTO);

}
