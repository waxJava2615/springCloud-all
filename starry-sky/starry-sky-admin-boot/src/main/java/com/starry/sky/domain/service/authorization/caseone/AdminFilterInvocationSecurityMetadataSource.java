package com.starry.sky.domain.service.authorization.caseone;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author wax
 * @description: 自定义的资源（url）权限（角色）数据获取类
 * @date 2021-08-26
 */
//@Component
public class AdminFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {



    private Map<String,Collection<ConfigAttribute>> loadResourcePermission(){
        // 获取所有角色

        // 获取资源需要的角色

        // 解析资源对应的权限集合


        ConfigAttribute adminRole = new SecurityConfig("ROLE_ADMIN");
        ConfigAttribute employeeRole = new SecurityConfig("ROLE_USER");

        List<ConfigAttribute> adminUrlRoles = new ArrayList<>();
        adminUrlRoles.add(adminRole);
        List<ConfigAttribute> employeeUrlRoles = new ArrayList<>();
        employeeUrlRoles.add(employeeRole);

        Map<String,Collection<ConfigAttribute>> map = new HashMap<>();

        map.put("/inset", employeeUrlRoles);
//        map.put("/inset", employeeUrlRoles);
        map.put("/admin/login", null);

        return map;
    }



    /**
     * 表示当前请求URL所需的角色
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Map<String, Collection<ConfigAttribute>> resourcePermissionMap = loadResourcePermission();
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        // 遍历每个资源（url），如果与用户请求的资源（url）匹配，则返回该资源（url）所需要的权限（角色）集合
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourcePermissionMap.entrySet()){
            if (new AntPathRequestMatcher(entry.getKey().trim()).matches(request)){
                return entry.getValue();
            }
        }
        // 不需要权限（角色）访问
        return null;
    }


    /**
     * 方法用来返回所有定义好的权限资源，SpringSecurity在启动时会校验相关配置是否正确
     * 如果不需要校验，直接返回null即可
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 返回类对象是否支持校验
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
