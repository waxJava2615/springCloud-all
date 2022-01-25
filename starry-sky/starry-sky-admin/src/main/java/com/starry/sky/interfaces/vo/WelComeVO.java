package com.starry.sky.interfaces.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author wax
 * @description: 欢迎页面数据加载
 * @date 2021-11-30
 */
@Getter
@Setter
@NoArgsConstructor
public class WelComeVO extends BaseVO{

    /**
     * 用户信息
     */
    private AdminUserVO userInfo;

    /**
     * 左侧菜单
     */
    private List<AdminMenuVO> leftMenu;

    /**
     * 顶侧菜单
     */
    private List<AdminMenuVO> topMenu;

}
