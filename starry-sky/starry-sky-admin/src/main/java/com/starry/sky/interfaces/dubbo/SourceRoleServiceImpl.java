package com.starry.sky.interfaces.dubbo;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Lists;
import com.starry.sky.application.dto.AdminRoleDTO;
import com.starry.sky.application.service.SourceRoleAppService;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;
import com.starry.sky.interfaces.vo.AdminRoleVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * @author wax
 * @description: 资源和操作
 * @date 2021-11-15
 */
@DubboService(version = "1.0.0",group = CommonConstants.DUBBO_SUB_STARRY_SKY_ADMIN,interfaceClass = ISourceRoleService.class,timeout =
        50000)
public class SourceRoleServiceImpl implements ISourceRoleService {


    @Autowired
    SourceRoleAppService sourceRoleAppService;

    /**
     * 获取资源和操作对应的权限集合
     *
     * @return
     */
    @Override
    public List<AdminRoleVO> getSourceRole() {
        List<AdminRoleVO> listResult = Lists.newArrayList();
        SysAdminRoleDTO sysAdminRoleDTO = new SysAdminRoleDTO();
        sysAdminRoleDTO.defaultPage();
        sysAdminRoleDTO.setHide(CommonConstants.HIDE_NO);
        List<AdminRoleDTO> list = sourceRoleAppService.getSourceOperationRole(sysAdminRoleDTO);
        if (list == null){
            list = Collections.emptyList();
        }
        list.forEach(r ->{
            AdminRoleVO adminRoleVO = BeanUtil.toBean(r, AdminRoleVO.class);
            listResult.add(adminRoleVO);
        });
        return listResult;
    }
}
