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
public class SysAdminPermissionOperationRelation implements Serializable {
    /**
    * 权限ID
    */
    private Long permissionId;

    /**
    * 操作ID
    */
    private Long operationId;

    private static final long serialVersionUID = 1L;
}