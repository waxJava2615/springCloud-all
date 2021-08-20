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
public class SysAdminPermission implements Serializable {
    /**
    * 权限ID
    */
    private Long id;

    /**
    * 权限类型 0菜单  1操作
    */
    private Integer type;

    private static final long serialVersionUID = 1L;
}