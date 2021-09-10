package com.starry.sky;

import java.lang.annotation.*;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-09
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface CustomCacheKeyScans {

    public String name() default "default";

}
