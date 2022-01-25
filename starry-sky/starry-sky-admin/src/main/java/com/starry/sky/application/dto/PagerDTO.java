package com.starry.sky.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @description: 数据分页
 * @date 2021-12-17
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagerDTO<T> implements Serializable {

    private long pageNo;

    private long pageSize;

    private long total;

    /**
     * 页面操作权限  push del edit export
     */
//    private List<String> operationList;

    private Map<String,AdminOperationDTO> roleOperation;

    private List<T> list;


    public PagerDTO (long pageNo,long pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PagerDTO (long pageNo,long pageSize,long total){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
    }


}
