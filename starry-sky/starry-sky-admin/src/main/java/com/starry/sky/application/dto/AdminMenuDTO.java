package com.starry.sky.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author wax
 * @description: 菜单dto
 * @date 2021-11-23
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminMenuDTO extends BaseDTO{


    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单地址
     */
    private String url;


    /**
     * 可做路由名称  当前唯一
     */
    private String onlyKey;

    /**
     * 菜单父ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;


    private AdminMenuDTO parentMenu;


    private AdminMenuDTO childrenMenu;


    /**
     * 位置 左边  或者 头部
     */
    private Integer option;

    /**
     * 样式或者图片
     */
    private String icon;

    /**
     * 地址映射的权限集合
     */
//     private Collection<String> authorities;

    // 是否拥有访问权
    private Boolean auth;


    private List<AdminMenuDTO> listChildren;

}
