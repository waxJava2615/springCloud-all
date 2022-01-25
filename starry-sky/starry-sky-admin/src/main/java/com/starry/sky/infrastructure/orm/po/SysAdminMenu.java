package com.starry.sky.infrastructure.orm.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-30
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@TableName(value = "sys_admin_menu")
public class SysAdminMenu extends BaseEntity {
    
    /**
     * 菜单名称
     */
    @TableField(value = "name",fill = FieldFill.INSERT_UPDATE)
    private String name;

    /**
     * 菜单地址
     */
    @TableField(value = "url",fill = FieldFill.INSERT_UPDATE)
    private String url;


    @TableField(value = "only_key",fill = FieldFill.INSERT_UPDATE)
    private String onlyKey;

    /**
     * 菜单父ID
     */
    @TableField(value = "parent_id",fill = FieldFill.INSERT_UPDATE)
    private Long parentId;

    /**
     * 位置 左边  或者 头部
     */
    @TableField(value = "`option`",fill = FieldFill.INSERT_UPDATE)
    private int option;

    /**
     * 样式或者图片
     */
    @TableField(value = "icon",fill = FieldFill.INSERT_UPDATE)
    private String icon;


}