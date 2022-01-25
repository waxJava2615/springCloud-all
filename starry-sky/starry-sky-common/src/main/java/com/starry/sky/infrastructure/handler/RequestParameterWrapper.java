package com.starry.sky.infrastructure.handler;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author wax
 * @description: 重写 getReader() 方法进行处理，因为在controller
 * 使用@RequestBody 在反序列化到对象数据读取就是通过该方法读取，
 * 所以可以通过在这修改数据达到最终过滤数据的
 * @date 2021-09-23
 */
public class RequestParameterWrapper extends HttpServletRequestWrapper{
    private Map<String, String[]> params = new HashMap<>();
    public RequestParameterWrapper(HttpServletRequest request) {
        super(request);
        //将现有parameter传递给params
        this.params.putAll(request.getParameterMap());
    }


    public RequestParameterWrapper(HttpServletRequest request, Map<String, Object> extendParams) {
        this(request);
        //这里将扩展参数写入参数表
        addParameters(extendParams);
    }

    public void addParameters(Map<String, Object> extraParams) {
        for (Map.Entry<String, Object> entry : extraParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }
    /**
     * 重写getParameter，代表参数从当前类中的map获取
     *
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * The default behavior of this method is to return getParameterMap()
     * on the wrapped request object.
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }


    @Override
    public String[] getParameterValues(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values;
    }


    /**
     * 在获取所有的参数名,必须重写此方法，否则对象中参数值映射不上
     * @return
     */
    @Override
    public Enumeration<String> getParameterNames() {
        return new Vector(params.keySet()).elements();
    }


    private String getData(Map<String, Object> paramsMap){
        if (paramsMap == null || paramsMap.isEmpty()){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        String data = null;
        try {
            data = mapper.writeValueAsString(paramsMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    /**
     * 添加参数
     *
     * @param name
     * @param value
     */
    private void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }
}
