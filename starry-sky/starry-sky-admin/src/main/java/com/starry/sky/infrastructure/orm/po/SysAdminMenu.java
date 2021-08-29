package com.starry.sky.infrastructure.orm.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

  /**  
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
@Getter
@Setter
@NoArgsConstructor
public class SysAdminMenu extends BaseEntity {
    /**
    * 菜单ID
    */
    private Long id;

    /**
    * 菜单名称
    */
    private String name;

    /**
    * 菜单地址
    */
    private String url;

    /**
    * 菜单父ID
    */
    private Long parent;

    private static final long serialVersionUID = 1L;
}