package com.starry.sky.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
    private String hide;

    /**
     * 排序
     */
    @TableField(value = "`order`")
    private String order;


    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}
