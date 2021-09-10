package com.starry.sky.infrastructure.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.starry.sky.common.params.BaseParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author wax
 * @description: 基本参数
 * @date 2021-09-09
 */
@Getter
@Setter
@NoArgsConstructor
public class SysAdminParam extends BaseParam {

    private Long id;

    /**
     * 操作IP
     */
    private String ip;

    /**
     * 隐藏显示
     */
    private Integer hide;

    /**
     * 排序
     */
    private Long order;


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
