package com.starry.sky.common.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wax
 * @description: 请求相应类
 * @date 2021-08-20
 */
@Getter
@Setter
@NoArgsConstructor
public class ResultData<T> implements Serializable {

    private int code;

    private String msg;

    private T data;


    public static ResultData customizeResult(int code, String msg) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMsg(msg);
        return resultData;
    }

    public static ResultData customizeResult(int code, String msg, Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    public static ResultData successResult() {
        ResultData resultData = new ResultData();
        resultData.setCode(ResultCode.SUCCESS.getCode());
        resultData.setMsg(ResultCode.SUCCESS.getMessage());
        return resultData;
    }

    public static ResultData failResult() {
        ResultData resultData = new ResultData();
        resultData.setCode(ResultCode.FAIL.getCode());
        resultData.setMsg(ResultCode.FAIL.getMessage());
        return resultData;
    }

}
