package com.starry.sky.infrastructure.dto;

import com.starry.sky.infrastructure.constant.CommonConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wax
 * @description: 数据传输对象
 * @date 2021-09-16
 */
@Getter
@Setter
@NoArgsConstructor
public class SysAdminDTO extends BaseDTO implements Serializable {

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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private Long updatedBy;


    /**
     * 是否执行count
     */
    private Boolean showTotal;


    public void defaultPage(){
        this.setPageNo(1);
        this.setPageSize(Integer.MAX_VALUE);
        this.setHide(CommonConstants.HIDE_NO);
    }

    public void defaultPage(int hide){
        this.setPageNo(1);
        this.setPageSize(Integer.MAX_VALUE);
        this.setHide(hide);
    }

}
