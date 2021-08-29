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
public class SysAdminRole extends BaseEntity {
    /**
    * 角色ID
    */
    private Long id;

    /**
    * 角色名称
    */
    private String name;

    private static final long serialVersionUID = 1L;
}