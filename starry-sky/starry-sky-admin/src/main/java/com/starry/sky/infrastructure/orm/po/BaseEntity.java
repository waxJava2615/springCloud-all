package com.starry.sky.infrastructure.orm.po;

import com.baomidou.mybatisplus.annotation.TableField;
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
    
    private static final long serialVersionUID = -5411089503770543726L;
    /**
     * 对象ID
     */
    @TableField(value = "id")
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
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    public static <T extends BaseEntity> T createDefault(){
        T baseEntity = (T) new BaseEntity();
        baseEntity.setId(Long.MAX_VALUE);
        return baseEntity;
    }


}
