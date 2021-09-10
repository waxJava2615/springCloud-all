package com.starry.sky;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-09
 */
@CustomCacheKeyScans(name = "user")
public class UserCacheKeyImpl implements CacheKey {



    @CustomGenerateCacheKey
    public String generateA(UserParams userParams){
        return String.format("generateA-%s",userParams.getId());
    }

    @CustomGenerateCacheKey
    public String generateB(UserParams userParams){
        return String.format("generateB-%s",userParams.getName());
    }

    @CustomGenerateCacheKey
    public String generateC(UserParams userParams){
        return String.format("generateC-%s-%s",userParams.getId(),userParams.getName());
    }


    @Override
    public boolean support(Class clazz){
        return UserParams.class.isAssignableFrom(clazz);
    }

}
