package com.starry.sky.infrastructure.orm.po;

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
    @TableField(value = "name")
    private String name;

    /**
     * 菜单地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 菜单父ID
     */
    @TableField(value = "parent")
    private Long parentId;


}