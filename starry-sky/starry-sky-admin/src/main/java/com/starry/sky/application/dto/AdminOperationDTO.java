package com.starry.sky.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wax
 * @description: TODO
 * @date 2021-12-20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminOperationDTO extends BaseDTO {

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
     * 样式或者图片
     */
    private String icon;

    /**
     * 父ID
     */
    private Long parentId;


}
