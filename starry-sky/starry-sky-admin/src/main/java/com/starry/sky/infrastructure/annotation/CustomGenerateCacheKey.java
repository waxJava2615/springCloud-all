package com.starry.sky.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * @author wax
 * @description: 缓存key生成标识
 * @date 2021-09-09
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomGenerateCacheKey {


    /**
     * 一定要传入
     * @return
     */
    String[] useTable();



}
