package com.starry.sky.infrastructure.utils;

import java.util.Collection;

/**
 * @author wax
 * @description: 输出
 * @date 2021-09-23
 */
public class ArrayerUtils {


    /**
     * 判读集合是否空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection){
        return collection==null || collection.isEmpty();
    }


}
