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

    /**
     * 可做路由名称  当前唯一
     */
    private String onlyKey;

    /**
     * 位置 左边  或者 头部
     */
    private Integer option;

    /**
     * 样式或者图片
     */
    private String icon;



    private List<Long> listMenuId = new ArrayList<>();

}
