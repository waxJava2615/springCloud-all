package com.starry.sky.infrastructure.utils.validations;

import com.starry.sky.infrastructure.orm.po.BaseEntity;

import java.util.List;

/**
 * @author wax
 * @description: 是否初始值判断
 * @date 2021-09-22
 */
public class SysDefaultValueValidation {


    public static boolean verifyDefault(BaseEntity baseEntity){
        if (baseEntity != null && baseEntity.getId() == Long.MAX_VALUE){
            return true;
        }
        return false;
    }

    public static boolean verifyDefault(List<? extends BaseEntity> list){
        if (list != null && !list.isEmpty() && verifyDefault(list.get(0))){
            return true;
        }
        return false;
    }



}
