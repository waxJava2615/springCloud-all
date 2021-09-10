package com.starry.sky.domain.service.authorization.casetwo;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.starry.sky.domain.entity.*;
import com.starry.sky.domain.repository.*;
import com.starry.sky.infrastructure.exception.CustomizeAccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wax
 * @description: 获取角色资源信息
 * @date 2021-08-27
 */
public class AdminSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    
    
    private final FilterInvocationSecurityMetadataSource securityMetadataSource;
    
    
    private SysAdminRoleDORepository sysAdminRoleDORepository;
    
    
    private SysAdminMenuDORepository sysAdminMenuDORepository;
    
    
    private SysAdminOperationDORepository sysAdminOperationDORepository;
    
    
    private SysAdminRolePermissionRelationDORepository sysAdminRolePermissionRelationDORepository;
    
    
    private SysAdminPermissionMenuRelationDORepository sysAdminPermissionMenuRelationDORepository;
    
    
    private SysAdminPermissionOperationRelationDORepository sysAdminPermissionOperationRelationDORepository;
    
    /**
     * @return
     * @throws CustomizeAccessDeniedException
     */
    private Map<String, Collection<ConfigAttribute>> loadResourcePermission() {
        
        Map<String, Collection<ConfigAttribute>> resultMetadataMap = Maps.newHashMap();
        
        // 角色权限集合
        Multimap<Long, Long> permissionMappingRoleMap = HashMultimap.create();
        // 资源映射权限
        Multimap<Long, Long> menuMappingPermissionMap = HashMultimap.create();
        
        // 操作权限映射
        Multimap<Long, Long> operationMappingPermissionMap = HashMultimap.create();
        
        // 角色ID映射实体
        Map<Long, SysAdminRoleDO> roleDOMap = Maps.newHashMap();
        
        // 数据权限资源
        List<SysAdminMenuDO> listMenuDO = null;
        // 功能操作权限
        List<SysAdminOperationDO> listOptionDO = null;
        
        
        // 获取所有角色 拆分关联查询 有利于缓存
        List<SysAdminRoleDO> listRole = null;
        int pageNum = 1;
        int pageSize = 1;
        do{
            listRole = sysAdminRoleDORepository.findList(pageNum,pageSize);
            pageNum++;
        }while (listRole != null && !listRole.isEmpty());

        if (listRole == null || listRole.isEmpty()) {
            return resultMetadataMap;
        }
        // 获取角色ID
        List<Long> listRoleId =
                listRole.stream().map(SysAdminRoleDO::getId).collect(Collectors.toList());

        listRole.forEach(r ->  roleDOMap.put(r.getId(), r) );

        // 获取角色关联的权限
        List<SysAdminRolePermissionRelationDO> listRolePermissionRelation =
                sysAdminRolePermissionRelationDORepository.findByRoleId(listRoleId);
        if (listRolePermissionRelation == null || listRolePermissionRelation.isEmpty()) {
            return resultMetadataMap;
        }

        listRolePermissionRelation.forEach(pr -> {
            permissionMappingRoleMap.put(pr.getPermissionId(), pr.getRoleId());
        });

        // 获取权限ID
        List<Long> listPermissionId =
                listRolePermissionRelation.stream().map(p -> p.getId()).collect(Collectors.toList());
        // 获取权限关联的菜单  数据级别权限也就等于没有操作权限
        List<SysAdminPermissionMenuRelationDO> listPermissionMenuRelation =
                sysAdminPermissionMenuRelationDORepository.findByPermissionId(listPermissionId);
        if (listPermissionMenuRelation == null || listPermissionMenuRelation.isEmpty()) {
            return resultMetadataMap;
        }
        listPermissionMenuRelation.forEach(pm -> {
            menuMappingPermissionMap.put(pm.getMenuId(), pm.getPermissionId());
        });
        List<Long> listMenuId =
                listPermissionMenuRelation.stream().map(SysAdminPermissionMenuRelationDO::getMenuId).collect(Collectors.toList());


        if (!listMenuId.isEmpty()) {
            listMenuDO = sysAdminMenuDORepository.findByMenuId(listMenuId);
        }

        // 功能操作
        List<SysAdminPermissionOperationRelationDO> listPermissionOperationRelation =
                sysAdminPermissionOperationRelationDORepository.findByPermissionId(listPermissionId);
        if (listPermissionOperationRelation != null && !listPermissionOperationRelation.isEmpty()) {

            // 操作权限映射
            listPermissionOperationRelation.forEach(por -> {
                operationMappingPermissionMap.put(por.getOperationId(), por.getPermissionId());
            });

            List<Long> listOperationId =
                    listPermissionOperationRelation.stream().map(o -> o.getOperationId()).collect(Collectors.toList());

            if (!listOperationId.isEmpty()) {
                listOptionDO =
                        sysAdminOperationDORepository.findByOptionId(listOperationId);
            }
        }

        //解析数据有哪些角色
        if (listMenuDO != null) {
            listMenuDO.forEach(m -> {
                // 获取菜单权限集合
                Collection<Long> permissionIdCollection = menuMappingPermissionMap.get(m.getId());
                if (permissionIdCollection != null) {
                    permissionIdCollection.forEach(pic -> {
                        Collection<Long> roleIdCollection = permissionMappingRoleMap.get(pic);
                        if (roleIdCollection != null) {
                            ArrayList<ConfigAttribute> listConfigAttribute = Lists.newArrayList();
                            roleIdCollection.forEach(ric -> {
                                SysAdminRoleDO sysAdminRoleDO = roleDOMap.get(ric);
                                ConfigAttribute role = new SecurityConfig(sysAdminRoleDO.getName());
                                listConfigAttribute.add(role);
                                resultMetadataMap.put(m.getUrl(), listConfigAttribute);
                            });
                        }
                    });
                }
            });
        }
        // 解析操作有哪些角色
        if (listOptionDO != null) {
            listOptionDO.forEach(o -> {
                Collection<Long> permissionIdCollection =
                        operationMappingPermissionMap.get(o.getId());
                if (permissionIdCollection != null) {
                    permissionIdCollection.forEach(pic -> {
                        Collection<Long> roleIdCollection = permissionMappingRoleMap.get(pic);
                        if (roleIdCollection != null) {
                            ArrayList<ConfigAttribute> listConfigAttribute = Lists.newArrayList();
                            roleIdCollection.forEach(ric -> {
                                SysAdminRoleDO sysAdminRoleDO = roleDOMap.get(ric);
                                ConfigAttribute role = new SecurityConfig(sysAdminRoleDO.getName());
                                listConfigAttribute.add(role);
                                resultMetadataMap.put(o.getInterceptUrlPrefix(),
                                        listConfigAttribute);
                            });
                        }
                    });
                }
            });
        }
        return resultMetadataMap;
    }
    
    
    public AdminSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource,
                                       SysAdminRoleDORepository sysAdminRoleDORepository,
                                       SysAdminMenuDORepository sysAdminMenuDORepository,
                                       SysAdminOperationDORepository sysAdminOperationDORepository,
                                       SysAdminRolePermissionRelationDORepository sysAdminRolePermissionRelationDORepository,
                                       SysAdminPermissionMenuRelationDORepository sysAdminPermissionMenuRelationDORepository,
                                       SysAdminPermissionOperationRelationDORepository sysAdminPermissionOperationRelationDORepository) {
        this.securityMetadataSource = securityMetadataSource;
        this.sysAdminRoleDORepository = sysAdminRoleDORepository;
        this.sysAdminMenuDORepository = sysAdminMenuDORepository;
        this.sysAdminOperationDORepository = sysAdminOperationDORepository;
        this.sysAdminRolePermissionRelationDORepository = sysAdminRolePermissionRelationDORepository;
        this.sysAdminPermissionMenuRelationDORepository = sysAdminPermissionMenuRelationDORepository;
        this.sysAdminPermissionOperationRelationDORepository =
                sysAdminPermissionOperationRelationDORepository;
    }
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Collection<ConfigAttribute> attributes = securityMetadataSource.getAttributes(object);
        Map<String, Collection<ConfigAttribute>> resourcePermissionMap = loadResourcePermission();
        if (resourcePermissionMap == null) {
            return attributes;
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        // 遍历每个资源（url），如果与用户请求的资源（url）匹配，则返回该资源（url）所需要的权限（角色）集合
        for (Map.Entry<String, Collection<ConfigAttribute>> entry :
                resourcePermissionMap.entrySet()) {
            if (new AntPathRequestMatcher(entry.getKey().trim()).matches(request)) {
                attributes = new HashSet<>(entry.getValue());
            }
        }
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
