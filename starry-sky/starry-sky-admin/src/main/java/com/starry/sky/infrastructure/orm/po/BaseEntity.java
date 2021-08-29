package com.starry.sky.infrastructure.orm.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description po基类
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity implements Serializable {


    /**
     * 对象ID
     */
    private Long id;


    /**
     * 操作IP
     */
    private String ip;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;




}
