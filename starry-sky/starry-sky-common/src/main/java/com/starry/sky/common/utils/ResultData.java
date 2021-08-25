package com.starry.sky.common.utils;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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


    public static void printJson(HttpServletRequest request, HttpServletResponse response,String jsonData) throws IOException {
        try {
            Preconditions.checkNotNull(jsonData);
        } catch (Exception e) {
            return;
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(jsonData);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }



}
