package com.starry.sky.infrastructure.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author wax
 * @description: 资源
 * @date 2021-11-15
 */
@Getter
@Setter
@NoArgsConstructor
public class SourceRoleDTO {

    private String url;

    private List<String> roleList;


}
