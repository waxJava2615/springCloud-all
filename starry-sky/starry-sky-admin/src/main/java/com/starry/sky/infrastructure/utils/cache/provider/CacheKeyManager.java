package com.starry.sky.infrastructure.utils.cache.provider;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.starry.sky.infrastructure.annotation.CustomGenerateCacheKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @description: 当对象发送变化是调用此方法获取所有的KEY
 * @date 2021-09-09
 */
@Component
public class CacheKeyManager implements InitializingBean {

    Map<String, String> curentObjMap = Maps.newHashMap();
    Multimap<String, String> joinTableMap = ArrayListMultimap.create();


    @Autowired
    private List<? extends AbstractParamsCacheKey> cacheKeyList;


    @Override
    public void afterPropertiesSet() throws Exception {
        cacheKeyList.forEach(k->{
            Class<? extends AbstractParamsCacheKey> aClass = k.getClass();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                CustomGenerateCacheKey annotation = method.getAnnotation(CustomGenerateCacheKey.class);
                for (String table : annotation.useTable()) {
                    if (annotation.useTable().length >= 2) {
                        joinTableMap.put(table, k.generateJoinListManager());
                    } else {
                        curentObjMap.put(table, k.generateListManager());
                    }

                }
            }
        });
    }
}
