package com.starry.sky.common.utils;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wax
 * @description: 请求工具类
 * @date 2021-09-07
 */
@Getter
@Setter
public class ResUtils {

    private HttpServletRequest request;

    private  HttpServletResponse response;


    ResUtils(){
    }

    ResUtils(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
    /**
     * 获取IP
     */
    public String getIP(){
        final String unknown = "unknown";
        final String flag = ",";
        // 这个一般是Nginx反向代理设置的参数
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 处理多IP的情况（只取第一个IP）
        if ((ip != null) && flag.contains(ip)) {
            String[] ipArray = StringUtils.split(flag);
            ip = ipArray[0];
        }
        return ip;
    }






}
