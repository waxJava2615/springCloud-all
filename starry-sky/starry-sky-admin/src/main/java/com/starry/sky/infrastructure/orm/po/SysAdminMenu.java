package com.starry.sky.infrastructure.orm.po;

import java.io.Serializable;
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
public class SysAdminMenu implements Serializable {
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