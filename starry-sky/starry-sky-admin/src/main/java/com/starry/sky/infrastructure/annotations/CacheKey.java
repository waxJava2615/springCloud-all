package com.starry.sky.infrastructure.annotations;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-03
 */
public @interface CacheKey {

    String name() default "";

    int pageNo() default 1;

    int pageSize() default 10;

}
