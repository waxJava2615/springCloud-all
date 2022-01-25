package com.starry.sky.infrastructure.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-23
 */
public class NumberUtils {

    public static int getInt(Object obj){
        return getInt(obj,0);
    }

    public static int getInt(Object obj,int def){
        if (obj == null) {
            return def;
        }
        if (obj instanceof Integer) {
            return (Integer)obj;
        }
        try{
            String s = obj.toString();
            if (StringUtils.isEmpty(s)){
                return def;
            }
            return Integer.parseInt(s);
        }catch(Exception e){
            return def;
        }
    }


    public static long getLong(Object obj){
        return getLong(obj,0L);
    }

    public static long getLong(Object obj,long def){
        if (obj == null) {
            return def;
        }
        if (obj instanceof Long) {
            return (Long)obj;
        }
        try{
            String s = obj.toString();
            if(StringUtils.isEmpty(s)){
                return def;
            }
            return Long.parseLong(s);
        }catch(Exception e){
            return def;
        }
    }

    public static String getString(Object obj){
        return getString(obj,null);
    }
    public static String getString(Object obj,String def){
        if (obj == null) {
            return def;
        }
        if (obj instanceof String) {
            return (String)obj;
        }
        try{
            return obj.toString();
        }catch(Exception e){
            return def;
        }
    }


}
