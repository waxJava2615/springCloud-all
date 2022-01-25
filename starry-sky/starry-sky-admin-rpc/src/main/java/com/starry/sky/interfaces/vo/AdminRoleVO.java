package com.starry.sky.interfaces.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-16
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminRoleVO extends BaseVO {

    private String name;

    private String url;

    private List<String> roleList;
}
