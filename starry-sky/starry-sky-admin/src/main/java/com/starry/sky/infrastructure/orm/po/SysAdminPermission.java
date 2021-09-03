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
@TableName(value = "sys_admin_permission")
public class SysAdminPermission extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 权限类型
     */
    @TableField(value = "type")
    private String type;

}