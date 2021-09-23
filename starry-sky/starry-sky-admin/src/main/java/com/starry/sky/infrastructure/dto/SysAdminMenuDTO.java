package com.starry.sky.infrastructure.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wax
 * @description: 菜单传输对象
 * @date 2021-09-16
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysAdminMenuDTO extends SysAdminDTO {

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
    private Long parentId;



    private List<Long> listMenuId = new ArrayList<>();

}
