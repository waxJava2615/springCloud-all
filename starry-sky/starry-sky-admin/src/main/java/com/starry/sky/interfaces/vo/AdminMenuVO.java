package com.starry.sky.interfaces.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.starry.sky.application.dto.AdminMenuDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-30
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminMenuVO extends BaseVO{


    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单地址
     */
    private String url;


    private String onlyKey;

    /**
     * 菜单父ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;


    private AdminMenuVO parentMenu;

    private AdminMenuVO childrenMenu;

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
