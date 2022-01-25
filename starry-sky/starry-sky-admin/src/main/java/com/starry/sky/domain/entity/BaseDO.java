package com.starry.sky.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

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
    private String ip;
    
    /**
     * 隐藏显示
     */
    private Integer hide;
    
    /**
     * 排序
     */
    private Long order;
    
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Long updatedBy;
    
    
}
