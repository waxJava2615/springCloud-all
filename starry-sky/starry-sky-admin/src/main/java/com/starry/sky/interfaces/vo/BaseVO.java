package com.starry.sky.interfaces.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-16
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseVO implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
