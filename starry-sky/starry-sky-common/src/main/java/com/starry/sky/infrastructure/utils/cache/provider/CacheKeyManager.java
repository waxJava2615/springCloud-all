package com.starry.sky.infrastructure.utils.cache.provider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.SetMultimap;
import com.starry.sky.infrastructure.annotation.CustomGenerateCacheKey;
import com.starry.sky.infrastructure.utils.ArrayerUtils;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wax
 * @description: 当对象发送变化是调用此方法获取所有的KEY
 * @date 2021-09-09
 */
@Slf4j
@Component
public class CacheKeyManager implements InitializingBean {

    // 单表对象存储
    Map<String, String> objMap = Maps.newHashMap();
    // 单表对象分页查询
    Map<String, String> listMap = Maps.newHashMap();
    // 多表链接查询
    SetMultimap<String, String> joinTableMap = HashMultimap.create();


    @Autowired
    private List<? extends AbstractParamsCacheKey> cacheKeyList;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        cacheKeyList.forEach(k->{
            Class<? extends AbstractParamsCacheKey> aClass = k.getClass();
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                method.setAccessible(true);
                CustomGenerateCacheKey annotation = method.getAnnotation(CustomGenerateCacheKey.class);
                if (annotation != null){
                    String type = annotation.type();
                    for (String table : annotation.useTable()) {
                        if (CacheKeyConstants.SYS_TYPE_DEFAULT.equals(type)){
                            objMap.put(table,k.generateObjManager());
                        }else if (CacheKeyConstants.SYS_TYPE_LIST.equals(type)){
                            listMap.put(table,k.generateListManager());
                        }else {
//                            joinTableMap.put(table,k.generateJoinListManager());
                            try {
                                // 一定产生一个唯一KEY
                                String invoke = (String) method.invoke(k,null);
                                joinTableMap.put(table,invoke);
                            } catch (Exception e) {
                                log.error("解析可以异常");
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }


    /**
     * 删除 表对应的key
     * @param currentTable
     * @param mark
     */
    public void removeKey(String currentTable,String mark){
        List<String> delListKey = Lists.newArrayList();
        // 删除对象key
        String objKey = objMap.get(currentTable);
        if (StringUtils.isNotEmpty(objKey)){
            List<String> list = pullObjManager(objKey, mark);
            if (!ArrayerUtils.isEmpty(list)) {
                delListKey.addAll(list);
            }
        }
        // 删除对象分页查询
        String listKey = listMap.get(currentTable);
        if (StringUtils.isNotEmpty(listKey)){
            List<String> listManager = pullListManager(listKey);
            if (!ArrayerUtils.isEmpty(listManager)) {
                delListKey.addAll(listManager);
            }
        }
        // 删除连表查询对象
        Set<String> joinKeys = joinTableMap.get(currentTable);
        if (!ArrayerUtils.isEmpty(joinKeys)) {
            for (String joinKey : joinKeys) {
                List<String> listJoinKey = pullJoinTableManager(joinKey);
                if (!ArrayerUtils.isEmpty(listJoinKey)) {
                    delListKey.addAll(listJoinKey);
                }
            }
        }
        redissonClient.getKeys().delete(delListKey.toArray(new String[0]));
    }



    /**
     * 根据标识放入缓存中管理的KEY
     * @param cacheKey
     * @param mapKey    唯一的key  能找到唯一的对象
     * @param val
     */
    public void pushObjManager(String cacheKey,String mapKey,String val){
        List<String> list = pullObjManager(cacheKey,mapKey);
        if (list == null){
            list = new ArrayList<>();
        }
        if (!list.contains(val)){
            list.add(val);
            redissonClient.getMap(cacheKey).put(mapKey,list);
        }
    }

    public List<String> pullObjManager(String cacheKey,String mapKey){
        Object objects = redissonClient.getMap(cacheKey).get(mapKey);
        return objectMapper.convertValue(objects, new TypeReference<List<String>>() {});
    }


    /**
     * 分宜查询key管理
     * @param cacheKey
     * @param val
     */
    public void pushListManager(String cacheKey,String val){
        List<String> list = pullListManager(cacheKey);
        if (list == null){
            list = new ArrayList<>();
        }
        if (!list.contains(val)){
            list.add(val);
            redissonClient.getList(cacheKey).add(val);
        }
    }

    public List<String> pullListManager(String cacheKey){
        List<Object> objects = redissonClient.getList(cacheKey).readAll();
        return objectMapper.convertValue(objects, new TypeReference<List<String>>() {});
    }


    /**
     * 连表查询
     * @param cacheKey
     * @param val
     */
    public void pushJoinTableManager(String cacheKey,String val){
        List<String> list = pullJoinTableManager(cacheKey);
        if (list == null){
            list = new ArrayList<>();
        }
        if (!list.contains(val)){
            redissonClient.getList(cacheKey).add(val);
        }
    }

    public List<String> pullJoinTableManager(String cacheKey){
        Object objects = redissonClient.getList(cacheKey).readAll();
        return objectMapper.convertValue(objects, new TypeReference<List<String>>() {});
    }

}
