package com.starry.sky.interfaces.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.common.constant.CommonConstants;
import com.starry.sky.common.utils.ResultCode;
import com.starry.sky.common.utils.ResultData;
import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.domain.repository.SysAdminRoleDORepository;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;
import com.starry.sky.infrastructure.orm.repository.SysAdminMenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-20
 */
@Slf4j
@RestController
public class TestController {


    @Autowired
    private SysAdminMenuRepository sysAdminMenuRepository;


    @Autowired
    private SysAdminRoleDORepository sysAdminRoleDORepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("inset")
    public int inset() {
        SysAdminMenu sysAdminMenu = new SysAdminMenu();
        sysAdminMenu.setId(1L);
        sysAdminMenu.setName("测试");
        sysAdminMenu.setUrl("sssss");
        int insert = sysAdminMenuRepository.getBaseMapper().insert(sysAdminMenu);
        return insert;
    }


    @GetMapping("hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        String wax = (String) request.getAttribute("wax");
        return "hello" + wax;
    }

    @GetMapping("userRole.do")
    public ResultData userRole(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        SysAdminRoleDTO sysAdminRoleParam = SysAdminRoleDTO.builder()
                .build();
        sysAdminRoleParam.setPageNo(1);
        sysAdminRoleParam.setPageSize(10);
        sysAdminRoleParam.setHide(CommonConstants.HIDE_NO);
        List<SysAdminRoleDO> list = sysAdminRoleDORepository.findRolePermissionMenu(sysAdminRoleParam);
        return ResultData.customizeResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), list);
    }



}
