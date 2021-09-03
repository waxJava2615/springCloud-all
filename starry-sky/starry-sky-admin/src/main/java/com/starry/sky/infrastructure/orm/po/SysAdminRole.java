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
@TableName(value = "sys_admin_role")
public class SysAdminRole extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 角色名称
     */
    @TableField(value = "name")
    private String name;
}