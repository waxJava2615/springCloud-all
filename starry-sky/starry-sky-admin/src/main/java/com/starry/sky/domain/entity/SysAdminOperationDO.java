package com.starry.sky.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wax
 * @description: 实体映射Do
 * @date 2021-08-30
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SysAdminOperationDO extends BaseDO {
    /**
     * 操作名称
     */
    private String name;

    /**
     * 操作CODE
     */
    private Integer operationCode;

    /**
     * URL前缀
     */
    private String interceptUrlPrefix;

    /**
     * 样式或者图片
     */
    private String icon;

    /**
     * 父ID
     */
    private Long parentId;





}