package com.starry.sky.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @author wax
 * @description: 基类
 * @date 2021-09-03
 */
@Getter
@Setter
public class BaseDO implements Serializable {
    
    private Long id;
    
    /**
     * 操作IP
     */
    @TableField(value = "ip")
    private String ip;
    
    /**
     * 隐藏显示
     */
    @TableField(value = "hide")
    private Integer hide;
    
    /**
     * 排序
     */
    @TableField(value = "`order`")
    private Long order;
    
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_time")
    private Date updateTime;
    
    
    public Map<String, Object> toMap() {
        Map<String,Object> map = Maps.newHashMap();
        Class<?> forName = this.getClass();
        
        while (forName != null){
            Field[] fields = forName.getDeclaredFields();
            for (Field field : fields) {
                if ("class java.lang.String".equals(field.getGenericType().toString())){
                    Method method = null;
                    String val  = null;
                    try {
                        method = this.getClass().getMethod("get" + getMethodName(field.getName()));
                        val = (String)method.invoke(this);
                    } catch (Exception ignored) {
        
                    }
                    if (val != null){
                        map.put(field.getName(),val);
                    }
                }
                if ("class java.lang.Integer".equals(field.getGenericType().toString())){
                    Method method = null;
                    Integer val  = null;
                    try {
                        method = this.getClass().getMethod("get" + getMethodName(field.getName()));
                        val = (Integer)method.invoke(this);
                    } catch (Exception ignored) {
        
                    }
                    if (val != null){
                        map.put(field.getName(),val);
                    }
                }
    
                if ("class java.lang.Long".equals(field.getGenericType().toString())){
                    Method method = null;
                    Long val  = null;
                    try {
                        method = this.getClass().getMethod("get" + getMethodName(field.getName()));
                        val = (Long)method.invoke(this);
                    } catch (Exception ignored) {
        
                    }
                    if (val != null){
                        map.put(field.getName(),val);
                    }
                }
                if ("class java.util.Date".equals(field.getGenericType().toString())){
                    Method method = null;
                    Date val  = null;
                    try {
                        method = this.getClass().getMethod("get" + getMethodName(field.getName()));
                        val = (Date)method.invoke(this);
                    } catch (Exception ignored) {
        
                    }
                    if (val != null){
                        map.put(field.getName(),val);
                    }
                }
        
                if ("class java.lang.Double".equals(field.getGenericType().toString())){
                    Method method = null;
                    Double val  = null;
                    try {
                        method = this.getClass().getMethod("get" + getMethodName(field.getName()));
                        val = (Double)method.invoke(this);
                    } catch (Exception ignored) {
        
                    }
                    if (val != null){
                        map.put(field.getName(),val);
                    }
                }
                if ("class java.lang.Short".equals(field.getGenericType().toString())){
                    Method method = null;
                    Short val  = null;
                    try {
                        method = this.getClass().getMethod("get" + getMethodName(field.getName()));
                        val = (Short)method.invoke(this);
                    } catch (Exception ignored) {
                    
                    }
                    if (val != null){
                        map.put(field.getName(),val);
                    }
                }
            }
            forName = forName.getSuperclass();
        }
        
        return map;
    }
    
    
    // 把一个字符串的第一个字母大写
    private static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
    
    
    public static void main(String[] args) throws Exception {
        SysAdminUserDO baseDO = new SysAdminUserDO();
        baseDO.setCreateTime(new Date());
        baseDO.setPassword("asd");
        Map<String, Object> map = baseDO.toMap();
        System.out.println(map);
    }
    
}
