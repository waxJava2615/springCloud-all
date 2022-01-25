package com.starry.sky.interfaces.dto;

import com.starry.sky.infrastructure.dto.BaseDTO;
import com.starry.sky.infrastructure.utils.valid.EditGroup;
import com.starry.sky.infrastructure.utils.valid.PushGroup;
import com.starry.sky.infrastructure.utils.valid.RemoveGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author wax
 * @description: 请求参数
 * @date 2021-11-23
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO extends BaseDTO {

    @NotNull(groups = {EditGroup.class, RemoveGroup.class},message = "ID不存在")
    private Long id;

    /**
     * 菜单名称
     */
    @NotBlank(groups = {EditGroup.class, PushGroup.class},message = "请输入菜单名称")
    private String name;

    /**
     * 菜单地址
     */
    @NotBlank(groups = {EditGroup.class, PushGroup.class},message = "请输入菜单地址")
    private String url;


    /**
     * 可做路由名称  当前唯一
     */
    @NotBlank(groups = {EditGroup.class, PushGroup.class},message = "请输入菜单唯一Key")
    private String onlyKey;

    /**
     * 菜单父ID
     */
    private Long parentId;


    /**
     * 位置 左边  或者 头部
     */
    private Integer option;

    /**
     * 样式或者图片
     */
    private String icon;


}
