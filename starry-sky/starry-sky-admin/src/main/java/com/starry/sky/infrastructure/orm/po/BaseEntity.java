package com.starry.sky.infrastructure.orm.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId
    @TableField(value = "id",fill = FieldFill.INSERT_UPDATE)
    private Long id = Long.MAX_VALUE;


    /**
     * 操作IP
     */
    @TableField(value = "ip",fill = FieldFill.INSERT_UPDATE)
    private String ip;

    /**
     * 隐藏显示
     */
    @TableField(value = "hide",fill = FieldFill.INSERT_UPDATE)
    private Integer hide;

    /**
     * 排序
     */
    @TableField(value = "`order`",fill = FieldFill.INSERT_UPDATE)
    private Long order;


    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT_UPDATE)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "updated_by",fill = FieldFill.INSERT_UPDATE)
    private Long updatedBy;

}
