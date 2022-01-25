package com.starry.sky;

import lombok.Setter;
import org.reflections.Reflections;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-09
 */
@Setter
public class CacheKeyManager implements InitializingBean {

    private List<Class<? extends CacheKey>> cacheKeyList = new ArrayList<>();


    public CacheKey getCacheKey(Class clazz) throws IllegalAccessException, InstantiationException {
        CacheKey cacheKeyProvider = null;
        for (Class<? extends CacheKey> aClass : cacheKeyList) {
            CacheKey cacheKey = aClass.newInstance();
            if (cacheKey.support(clazz)) {
                cacheKeyProvider = aClass.newInstance();
            }
        }
        return cacheKeyProvider;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        // 获取注解注释的类
        Reflections reflections = new Reflections("com.starry.sky");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(CustomCacheKeyScans.class);
        System.out.println(annotated);
        for (Class<?> aClass : annotated) {
            cacheKeyList.add(((CacheKey) aClass.newInstance()).getClass());
        }

    }

    public static void main(String[] args) throws Exception {
        UserParams userParams = new UserParams();
        userParams.setId(123L);
        userParams.setName("wax");
        CacheKeyManager cacheKeyManager = new CacheKeyManager();
        cacheKeyManager.afterPropertiesSet();
        CacheKey cacheKey = cacheKeyManager.getCacheKey(UserParams.class);
        System.out.println(cacheKey);


        Class<? extends CacheKey> aClass = cacheKey.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            CustomGenerateCacheKey annotation = method.getAnnotation(CustomGenerateCacheKey.class);
            if (annotation != null) {
                String str = (String) method.invoke(cacheKey, userParams);
                System.out.println(str);
            }
        }


    }
}
