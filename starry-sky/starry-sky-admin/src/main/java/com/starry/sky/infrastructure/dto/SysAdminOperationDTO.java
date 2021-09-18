package com.starry.sky.infrastructure.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wax
 * @description: 菜单查询参数
 * @date 2021-09-13
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysAdminOperationDTO extends SysAdminDTO{

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


    private List<Long> listOperationId = new ArrayList<>();


}
