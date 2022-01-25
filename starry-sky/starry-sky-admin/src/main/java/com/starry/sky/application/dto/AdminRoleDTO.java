package com.starry.sky.application.dto;

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
public class AdminRoleDTO extends BaseDTO {

    private String name;

    private String url;

    private List<String> roleList;
}
