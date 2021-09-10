package com.starry.sky.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
    
    
}
