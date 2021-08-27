package com.starry.sky.domain.service.authorization.casetwo;

import com.starry.sky.domain.repository.SysAdminRoleRepository;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author wax
 * @description: 获取角色资源信息
 * @date 2021-08-27
 */
public class AdminSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{


    private final FilterInvocationSecurityMetadataSource securityMetadataSource;

    private SysAdminRoleRepository sysAdminRoleRepository;



    private Map<String,Collection<ConfigAttribute>> loadResourcePermission(){
        // 获取所有角色

        // 获取资源需要的角色

        // 解析资源对应的权限集合
        ConfigAttribute adminRole = new SecurityConfig("admin");
        ConfigAttribute employeeRole = new SecurityConfig("user");
        ConfigAttribute defaultRole = new SecurityConfig("anonymous");

        List<ConfigAttribute> adminUrlRoles = new ArrayList<>();
        adminUrlRoles.add(adminRole);
        List<ConfigAttribute> employeeUrlRoles = new ArrayList<>();
        employeeUrlRoles.add(employeeRole);
        List<ConfigAttribute> defaultUrlRole = new ArrayList<>();
        defaultUrlRole.add(employeeRole);

        Map<String,Collection<ConfigAttribute>> map = new HashMap<>();

        map.put("/inset", employeeUrlRoles);
//        map.put("/inset", employeeUrlRoles);
        map.put("/admin/login", null);

        return map;
    }


    public AdminSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Collection<ConfigAttribute> attributes = securityMetadataSource.getAttributes(object);
        Map<String, Collection<ConfigAttribute>> resourcePermissionMap = loadResourcePermission();
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        // 遍历每个资源（url），如果与用户请求的资源（url）匹配，则返回该资源（url）所需要的权限（角色）集合
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourcePermissionMap.entrySet()){
            if (new AntPathRequestMatcher(entry.getKey().trim()).matches(request)){
                attributes = new HashSet<>(entry.getValue());
            }
        }
        // 不需要权限（角色）访问
        return attributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
