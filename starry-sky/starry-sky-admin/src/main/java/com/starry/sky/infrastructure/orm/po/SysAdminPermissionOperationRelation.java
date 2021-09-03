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
@TableName(value = "sys_admin_permission_operation_relation")
public class SysAdminPermissionOperationRelation extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 权限ID
     */
    @TableField(value = "permission_id")
    private Long permissionId;

    /**
     * 操作ID
     */
    @TableField(value = "operation_id")
    private Long operationId;
}