package com.starry.sky.infrastructure.orm.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

  /**  
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SysAdminOperation  extends BaseEntity {
    /**
    * 操作ID
    */
    private Long id;

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
    * 父ID
    */
    private Long parentId;

    private static final long serialVersionUID = 1L;
}