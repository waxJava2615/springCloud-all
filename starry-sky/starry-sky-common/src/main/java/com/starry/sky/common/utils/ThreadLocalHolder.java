package com.starry.sky.common.utils;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-06
 */
public class ThreadLocalHolder {

    private static ThreadLocal<Boolean> ignoreCache = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    private static ThreadLocal<ResUtils> resUtils = new ThreadLocal<ResUtils>(){
        @Override
        protected ResUtils initialValue() {
            return new ResUtils();
        }
    };

    public static boolean getIgnoreCache(){
        return ignoreCache.get() != null && ignoreCache.get().equals(Boolean.TRUE);
    }

    public static void setIgnoreCache(){
        ignoreCache.set(Boolean.TRUE);
    }


    public static ThreadLocal<ResUtils> getResUtils() {
        return resUtils;
    }



    public static void removeAll(){
        ignoreCache.remove();
        resUtils.remove();
    }

}
