package com.starry.sky.infrastructure.utils;

import cn.hutool.core.net.URLDecoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.constant.HandlerConstants;
import com.starry.sky.infrastructure.dto.JWTPayloadDTO;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

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

    public String getOAuth2ClientId() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 从请求路径中获取
        String clientId = request.getParameter(CommonConstants.CLIENT_ID_KEY);
        if (StringUtils.isNotBlank(clientId)) {
            return clientId;
        }

        // 从请求头获取
        String basic = request.getHeader(CommonConstants.AUTHORIZATION_KEY);
        if (StringUtils.isNotBlank(basic) && basic.startsWith(CommonConstants.BASIC_PREFIX)) {
            basic = basic.replace(CommonConstants.BASIC_PREFIX, Strings.EMPTY);
            String basicPlainText = new String(Base64.getDecoder().decode(basic.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            clientId = basicPlainText.split(":")[0]; //client:secret
        }
        return clientId;
    }


    public String getParam(String paramName) {
        return request.getParameter(paramName);
    }

    public String getParam(String paramName,String def) {
        String parameter = request.getParameter(paramName);
        if (StringUtils.isNotBlank(parameter)){
            return parameter;
        }
        return def;
    }

    public int getIntParam(String paramName){
        return NumberUtils.getInt(paramName);
    }


    public int getIntParam(String paramName,int def){
        return NumberUtils.getInt(paramName,def);
    }

    public String getHandlerName(String handlerName){
        return request.getHeader(handlerName);
    }

    private String getHandlerJWTPayload(){
        String handlerName = this.getHandlerName(HandlerConstants.JWT_TOKEN_PAYLOAD);
        return URLDecoder.decode(handlerName, CommonConstants.UTF_8);
    }

    private JWTPayloadDTO getJWTPayload(){
        String payload = this.getHandlerJWTPayload();
        try {
            return ((ObjectMapper)BeanUtil.getBean("jacksonObjectMapper")).readValue(payload, JWTPayloadDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPayloadClientId(){
        JWTPayloadDTO payloadDTO = this.getJWTPayload();
        if (payloadDTO == null) {
            return null;
        }
        return payloadDTO.getClientId();
    }


    public Long getPayloadUserId(){
        JWTPayloadDTO payloadDTO = this.getJWTPayload();
        if (payloadDTO == null) {
            return null;
        }
        return payloadDTO.getId();
    }


    public List<String> getPayloadAuthorities(){
        JWTPayloadDTO payloadDTO = this.getJWTPayload();
        if (payloadDTO == null) {
            return Collections.emptyList();
        }
        return payloadDTO.getAuthorities();
    }


    public String getRequestURI(){
        return request.getRequestURI();
    }

    /**
     * 通过请求地址获取模块
     */
    public List<String> getModuleFullPath(){
        String uri = this.getRequestURI();
        List<String> listPath =  Collections.emptyList();
        if (StringUtils.isNotEmpty(uri)){
            String newPath = uri.substring(0, uri.lastIndexOf("/"));
            String[] path = StringUtils.split(newPath,"\\/");
            if (path != null && path.length > 0){
                listPath = new ArrayList<>();
                Collections.addAll(listPath,path);
            }
        }
        return listPath;
    }

    public String getModulePath(){
        List<String> moduleFullPath = getModuleFullPath();
        if (CollectionUtils.isNotEmpty(moduleFullPath)){
            return moduleFullPath.get(getModuleFullPath().size()-1);
        }
        return null;
    }


}
