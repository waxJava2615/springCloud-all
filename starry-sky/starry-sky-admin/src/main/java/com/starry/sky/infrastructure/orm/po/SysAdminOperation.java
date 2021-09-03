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
@TableName(value = "sys_admin_operation")
public class SysAdminOperation extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 操作名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 操作CODE
     */
    @TableField(value = "operation_code")
    private Integer operationCode;

    /**
     * URL前缀
     */
    @TableField(value = "intercept_url_prefix")
    private String interceptUrlPrefix;

    /**
     * 父ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

}