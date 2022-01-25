package com.starry.sky.infrastructure.utils.cache.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.utils.ThreadLocalHolder;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyGenerate;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wax
 * @description: 缓存抽象类
 * @date 2021-09-09
 */
@Slf4j
@Getter
@Setter
public abstract class AbstractParamsCacheKey implements CacheKeyGenerate, CacheProvider {

    @Autowired
    RedissonClient redissonClient;


    @Autowired
    ObjectMapper objectMapper;


//    @Override
//    public List<String> getCacheKeyList(SysAdminDTO sysAdminDTO){
//        List<String> list = Lists.newArrayList();
//        Class<? extends AbstractParamsCacheKey> aClass = this.getClass();
//        Method[] methods = aClass.getMethods();
//        for (Method method : methods) {
//            CustomGenerateCacheKey annotation = method.getAnnotation(CustomGenerateCacheKey.class);
//            if (annotation != null){
//                String result = null;
//                try {
//                    String[] joinTable = annotation.joinTable();
//                    if (joinTable.length < 2){
//                        result = (String) method.invoke(this, sysAdminDTO);
//                    }else {
//                        for (String t : joinTable) {
//                            if (t.equals(sysAdminDTO.getCurrentTable())){
//                                result = (String) method.invoke(this, joinTable);
//                            }
//                        }
//                    }
//                    if (result != null){
//                        list.add(result);
//                    }
//                } catch (IllegalAccessException e) {
//                    log.error("调用生成方法失败,无权执行",e);
//                } catch (InvocationTargetException e) {
//                    log.error("调用生成方法失败",e);
//                }
//            }
//        }
//        return list;
//    }


    /**
     * 表连接KEY生成
     * @param tables
     * @return
     */
    public String generateJoinKey(String currentTable,String ...tables) {
        StringBuffer sb = new StringBuffer();
        List<String> listTable = new ArrayList<>();
        for (String t : tables) {
            listTable.add(t);
        }
        sb.append(currentTable);
        sb.append("-join");
        //升序
        listTable.sort(Comparator.comparing(String::trim));
        listTable.forEach(t ->{
            sb.append("-").append(t);
        });
        return String.format("%s",sb.toString());
    }


    /**
     * 一个简单的SET
     * @param cacheKey
     * @param object
     */
    @Override
    public void set(String cacheKey, Object object,long expiry) {
        if (ThreadLocalHolder.getIgnoreCache()){
            return;
        }
        if (StringUtils.isEmpty(cacheKey)){
            return ;
        }
        if (!StringUtils.isEmpty(cacheKey)){
            redissonClient.getBucket(cacheKey).set(object,
                    CommonConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
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
        bucket.expire(CommonConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
        return (T) bucket.get();
    }


    @Override
    public void setList(String cacheKey, List<?> listEntity,long expiry) {
        if (ThreadLocalHolder.getIgnoreCache()){
            return ;
        }
        if (StringUtils.isEmpty(cacheKey)){
            return ;
        }
        RList<Object> list = redissonClient.getList(cacheKey);
        list.addAll(listEntity);
        list.expire(CommonConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
    }

    @Override
    public <T> List<T> getList(String cacheKey, Class<T> tClass) {
        if (ThreadLocalHolder.getIgnoreCache()){
            return Collections.emptyList();
        }
        if (StringUtils.isEmpty(cacheKey)){
            return Collections.emptyList();
        }
        RList<Object> list = redissonClient.getList(cacheKey);
        list.expire(CommonConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
        return (List<T>) list.readAll();
    }
}
