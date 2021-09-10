package com.starry.sky;

import java.lang.annotation.*;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-09
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomGenerateCacheKey {


    public String name() default "";


}
