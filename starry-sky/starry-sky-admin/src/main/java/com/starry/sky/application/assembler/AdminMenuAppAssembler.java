package com.starry.sky.application.assembler;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.starry.sky.application.dto.AdminMenuDTO;
import com.starry.sky.domain.entity.SysAdminMenuDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-25
 */
@Service
public class AdminMenuAppAssembler {


    /**
     *
     * @param sysAdminMenuDO
     * @return
     */
    public AdminMenuDTO convertDTO(SysAdminMenuDO sysAdminMenuDO){
        if (sysAdminMenuDO == null){
            return null;
        }
        return BeanUtil.copyProperties(sysAdminMenuDO, AdminMenuDTO.class);
//        Collection<String> authorities = sysAdminMenuDO.getAuthorities();
//        if (CollectionUtils.isNotEmpty(authorities)){
//            ThreadLocal<ResUtils> threadLocal = ThreadLocalHolder.getResUtils();
//            ResUtils resUtils = threadLocal.get();
//            List<String> listAuthorities = resUtils.getPayloadAuthorities();
//            if (CollectionUtils.isEmpty(listAuthorities)){
//                listAuthorities = Collections.emptyList();
//                adminMenuDTO.setAuth(Boolean.TRUE);
//            }
//            for (String la : listAuthorities) {
//                if (!authorities.contains(la)) {
//                    adminMenuDTO.setAuth(Boolean.TRUE);
//                } else {
//                    adminMenuDTO.setAuth(Boolean.FALSE);
//                    break;
//                }
//            }
//        }
//        return Boolean.TRUE.equals(adminMenuDTO.getAuth())?null:adminMenuDTO;
    }

    /**
     * 通过根菜单 转换菜单树
     * @param listMenu
     * @return
     */
    public List<AdminMenuDTO> convertTreeDTO(List<SysAdminMenuDO> listMenu){
        if (CollectionUtils.isEmpty(listMenu)){
            return Collections.emptyList();
        }
        // 获取跟路径集合
        List<AdminMenuDTO> listMenuRoot = new ArrayList<>();
        ListMultimap<Long,AdminMenuDTO> listMultimap = ArrayListMultimap.create();
        listMenu.forEach(m ->{
            AdminMenuDTO adminMenuDTO = convertDTO(m);
            if (adminMenuDTO != null){
                if (adminMenuDTO.getParentId() != null && adminMenuDTO.getParentId() > 0){
                    listMultimap.put(adminMenuDTO.getParentId(),adminMenuDTO);
                }
                if (adminMenuDTO.getParentId() == null || adminMenuDTO.getParentId() == 0){
                    listMenuRoot.add(adminMenuDTO);
                }
            }
        });
        return childMenu(listMenuRoot, listMultimap);
    }

    /**
     * 获取每个菜单下的子菜单
     * @param listMenu
     * @return
     */
    public List<AdminMenuDTO> convertDTO(List<SysAdminMenuDO> listMenu){
        if (CollectionUtils.isEmpty(listMenu)){
            return Collections.emptyList();
        }
        // 获取跟路径集合
        List<AdminMenuDTO> listMenuDTO = new ArrayList<>();
        ListMultimap<Long,AdminMenuDTO> listMultimap = ArrayListMultimap.create();
        listMenu.forEach(m ->{
            AdminMenuDTO adminMenuDTO = convertDTO(m);
            if (adminMenuDTO != null){
                listMenuDTO.add(adminMenuDTO);
                if (adminMenuDTO.getParentId() != null && adminMenuDTO.getParentId() > 0){
                    listMultimap.put(adminMenuDTO.getParentId(),adminMenuDTO);
                }
            }
        });
        return childMenu(listMenuDTO,listMultimap);
    }

    private List<AdminMenuDTO> childMenu(List<AdminMenuDTO> listRootMenu, ListMultimap<Long,AdminMenuDTO> listMultimap){
        if (CollectionUtils.isEmpty(listRootMenu)){
            return Collections.emptyList();
        }
        listRootMenu.forEach(l->{
            List<AdminMenuDTO> listChildren = listMultimap.get(l.getId());
            if (CollectionUtils.isNotEmpty(listChildren)){
                listChildren.stream().map(lc->childMenu(listChildren, listMultimap)).forEach(l::setListChildren);
            }
        });
        return listRootMenu;
    }


}
