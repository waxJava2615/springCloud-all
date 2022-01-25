package com.starry.sky.infrastructure.events.listener;

import com.starry.sky.infrastructure.events.CacheManagerApplicationEvent;
import com.starry.sky.infrastructure.events.sources.CacheManagerDTO;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author wax
 * @description: 数据变动监听器
 * @date 2021-09-15
 */
@Component
public class CacheManagerListener implements ApplicationListener<CacheManagerApplicationEvent> {




    @Override
    public void onApplicationEvent(CacheManagerApplicationEvent event) {
        CacheManagerDTO cacheManagerDTO = event.getCacheManagerDTO();
        if (cacheManagerDTO != null){

        }
    }
}
