package com.starry.sky.infrastructure.utils.cache.provider;

import com.google.common.collect.Lists;
import com.starry.sky.common.constant.StarrySkyAdminConstants;
import com.starry.sky.common.utils.ThreadLocalHolder;
import com.starry.sky.infrastructure.annotation.CustomGenerateCacheKey;
import com.starry.sky.infrastructure.param.SysAdminParam;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyGenerate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wax
 * @description: 缓存抽象类
 * @date 2021-09-09
 */
@Slf4j
public abstract class AbstractParamsCacheKey implements CacheKey, CacheKeyGenerate, CacheProvider {

    @Autowired
    RedissonClient redissonClient;


    /**
     * 获取所有Key
     * @param sysAdminParam
     * @return
     * @throws Exception
     */
    @Override
    public List<String> getCacheKeyList(SysAdminParam sysAdminParam){
        List<String> list = Lists.newArrayList();
        Class<? extends AbstractParamsCacheKey> aClass = this.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            CustomGenerateCacheKey annotation = method.getAnnotation(CustomGenerateCacheKey.class);
            if (annotation != null){
                String result = null;
                try {
                    String[] joinTable = annotation.joinTable();
                    if (joinTable.length < 2){
                        result = (String) method.invoke(this, sysAdminParam);
                    }else {
                        for (String t : joinTable) {
                            if (t.equals(sysAdminParam.getCurrentTable())){
                                result = (String) method.invoke(this, joinTable);
                            }
                        }
                    }
                    if (result != null){
                        list.add(result);
                    }
                } catch (IllegalAccessException e) {
                    log.error("调用生成方法失败,无权执行",e);
                } catch (InvocationTargetException e) {
                    log.error("调用生成方法失败",e);
                }
            }
        }
        return list;
    }


    /**
     * 表连接KEY生成
     * @param tables
     * @return
     */
    public String generateJoinKey(String ...tables) {
        StringBuffer sb = new StringBuffer();
        List<String> listTable = new ArrayList<>();
        for (String t : tables) {
            listTable.add(t);
        }
        //升序
        listTable.sort(Comparator.comparing(String::trim));
        listTable.forEach(t ->{
            sb.append("-").append(t);
        });
        return String.format("starry-sky%s",sb.toString());
    }


    /**
     * 一个简单的SET
     * @param cacheKey
     * @param object
     */
    @Override
    public void set(String cacheKey, Object object) {
        if (ThreadLocalHolder.getIgnoreCache()){
            return;
        }
        if (!StringUtils.isEmpty(cacheKey)){
            redissonClient.getBucket(cacheKey).setIfExists(object,
                    StarrySkyAdminConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
        }
    }

    /**
     * 一个简单的get
     * @param cacheKey
     * @param tClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T get(String cacheKey, Class<T> tClass) {
        if (ThreadLocalHolder.getIgnoreCache()){
            return null;
        }
        if (StringUtils.isEmpty(cacheKey)){
            return null;
        }
        RBucket<Object> bucket = redissonClient.getBucket(cacheKey);
        bucket.expire(StarrySkyAdminConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
        return (T) bucket.get();
    }

    /**
     * 缓存单个对象
     * @param cacheKey
     * @param mapKey
     * @param object
     */
    @Override
    public void setMap(String cacheKey, String mapKey, Object object) {
        if (ThreadLocalHolder.getIgnoreCache()){
            return;
        }
        RMap<Object, Object> map = redissonClient.getMap(cacheKey);
        if (!StringUtils.isEmpty(mapKey)){
            map.put(mapKey,object);
        }
        map.expire(StarrySkyAdminConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
    }

    /**
     * 获取单个对象
     * @param cacheKey
     * @param mapKey
     * @param tClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMap(String cacheKey, String mapKey, Class<T> tClass) {
        if (ThreadLocalHolder.getIgnoreCache()){
            return null;
        }
        RMap<String, Object> map = redissonClient.getMap(cacheKey);
        if (StringUtils.isEmpty(mapKey)){
            return null;
        }
        map.expire(StarrySkyAdminConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
        return (T) map.get(mapKey);
    }


    /**
     * 缓存当前对象列表
     * @param cacheKey
     * @param mapKey
     * @param list
     */
    @Override
    public void setMapForList(String cacheKey, String mapKey, List list) {
        if (ThreadLocalHolder.getIgnoreCache()){
            return;
        }
        RMap<String, Object> map = redissonClient.getMap(cacheKey);
        if (!StringUtils.isEmpty(mapKey)){
            map.put(mapKey,list);
        }
        map.expire(StarrySkyAdminConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
    }

    /**
     * 获取当前对象列表
     * @param cacheKey
     * @param mapKey
     * @param tClass
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> getMapForList(String cacheKey, String mapKey, Class<T> tClass) {
        if (ThreadLocalHolder.getIgnoreCache()){
            return null;
        }
        RMap<String, Object> map = redissonClient.getMap(cacheKey);
        if (StringUtils.isEmpty(mapKey)){
            return null;
        }
        map.expire(StarrySkyAdminConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
        return (List<T>) map.get(mapKey);
    }

    /**
     * 设置连表查询缓存
     *
     * @param cacheKey
     * @param mapKey
     * @param list
     */
    @Override
    public void setJoinForList(String cacheKey, String mapKey, List list) {
        if (ThreadLocalHolder.getIgnoreCache()){
            return;
        }
        RMap<String, Object> map = redissonClient.getMap(cacheKey);
        if (!StringUtils.isEmpty(mapKey)){
            map.put(mapKey,list);
        }
        map.expire(StarrySkyAdminConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
    }

    /**
     * 获取连表查询结果
     *
     * @param cacheKey
     * @param mapKey
     * @param tClass
     * @return
     */
    @Override
    public <T> List<T> getJoinForList(String cacheKey, String mapKey, Class<T> tClass) {
        if (ThreadLocalHolder.getIgnoreCache()){
            return null;
        }
        RMap<String, Object> map = redissonClient.getMap(cacheKey);
        if (StringUtils.isEmpty(mapKey)){
            return null;
        }
        map.expire(StarrySkyAdminConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
        return (List<T>) map.get(mapKey);
    }
}
