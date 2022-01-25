package com.starry.sky.infrastructure.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author wax
 * @description: 分页
 * @date 2021-12-17
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pager<T> implements Serializable {

    private long pageNo;

    private long pageSize;

    private long total;

    private List<T> list;

    public Pager (long pageNo,long pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Pager (long pageNo,long pageSize,long total){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
    }



    public boolean verifyDefault(){
        return this.pageNo == Long.MAX_VALUE;
    }

    public void defaultPage(){
        this.pageNo = Long.MAX_VALUE;
        this.total = 0;
    }
}
