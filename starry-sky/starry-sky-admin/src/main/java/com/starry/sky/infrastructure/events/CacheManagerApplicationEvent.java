package com.starry.sky.infrastructure.events;

import com.starry.sky.infrastructure.events.sources.CacheManagerDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author wax
 * @description: 缓存管理事件
 * @date 2021-09-22
 */
@Getter
public class CacheManagerApplicationEvent extends ApplicationEvent {

    private CacheManagerDTO cacheManagerDTO;

    public CacheManagerApplicationEvent(Object source) {
        super(source);
    }
    public CacheManagerApplicationEvent(Object source, CacheManagerDTO cacheManagerDTO) {
        super(source);
        this.cacheManagerDTO = cacheManagerDTO;
    }
}
