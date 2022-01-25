package com.starry.sky.interfaces.facade;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.application.dto.AdminMenuDTO;
import com.starry.sky.application.dto.PagerDTO;
import com.starry.sky.application.service.AdminMenuAppService;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.utils.ResUtils;
import com.starry.sky.infrastructure.utils.ResultData;
import com.starry.sky.infrastructure.utils.ThreadLocalHolder;
import com.starry.sky.infrastructure.utils.ValidationUtils;
import com.starry.sky.infrastructure.utils.enums.ResultCode;
import com.starry.sky.infrastructure.utils.valid.EditGroup;
import com.starry.sky.interfaces.dto.MenuDTO;
import com.starry.sky.interfaces.vo.AdminMenuVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wax
 * @description: 菜单入口
 * @date 2021-11-23
 */

@Slf4j
@RestController
public class MenuController {

    @Autowired
    AdminMenuAppService adminMenuAppService;


    @Autowired
    ObjectMapper objectMapper;


    /**
     * 获取菜单列表
     * @param menuDTO
     * @return
     */
    @SneakyThrows
    @RequestMapping("/menu/list.do")
    public ResultData loadMenuList(MenuDTO menuDTO){
        ThreadLocal<ResUtils> resUtilsThreadLocal = ThreadLocalHolder.getResUtils();
        ResUtils resUtils = resUtilsThreadLocal.get();
        log.info("MenuController method loadMenuList param:\t{}",
                objectMapper.writeValueAsString(resUtils.getRequest().getParameterMap()));
        String requestUri = resUtils.getRequestURI();
        log.info("MenuController method loadMenuList requestUri:\t{} \t requestFullPath:\t{}", requestUri,
                resUtils.getRequestURI());
        SysAdminMenuDTO sysAdminMenuDTO = new SysAdminMenuDTO();
        sysAdminMenuDTO.setPageNo(menuDTO.getPageNo());
        sysAdminMenuDTO.setPageSize(menuDTO.getPageSize());
        sysAdminMenuDTO.setId(menuDTO.getId());
        sysAdminMenuDTO.setParentId(menuDTO.getParentId());
        sysAdminMenuDTO.setName(menuDTO.getName());
        sysAdminMenuDTO.setHide(menuDTO.getHide());
        PagerDTO<AdminMenuDTO> pager = adminMenuAppService.loadMenuList(sysAdminMenuDTO);
        return ResultData.customizeResult(ResultCode.SUCCESS,pager);
    }

    /**
     * 通过菜单ID获取菜单信息
     * @param menuDTO
     * @return
     */
    @RequestMapping("/menu/select.do")
    public ResultData loadMenuSelect(MenuDTO menuDTO){
        log.info("MenuController method loadMenuSelect run:");
        SysAdminMenuDTO sysAdminMenuDTO = BeanUtil.copyProperties(menuDTO, SysAdminMenuDTO.class);
        AdminMenuDTO adminMenuDTO = adminMenuAppService.loadMenuSelect(sysAdminMenuDTO);
        AdminMenuVO adminMenuVO = BeanUtil.copyProperties(adminMenuDTO, AdminMenuVO.class,"createTime","updateTime");
        return ResultData.customizeResult(ResultCode.SUCCESS,adminMenuVO);
    }

    @RequestMapping("/menu/push.do")
    public ResultData pushMenu(MenuDTO menuDTO){
        log.info("MenuController method pushMenu run:");
        SysAdminMenuDTO sysAdminMenuDTO = BeanUtil.copyProperties(menuDTO, SysAdminMenuDTO.class);
        int pushMenu = adminMenuAppService.pushMenu(sysAdminMenuDTO);
        if (pushMenu <= 0){
            return ResultData.failResult();
        }
        return ResultData.successResult();
    }

    @RequestMapping("/menu/edit.do")
    public ResultData editMenu(MenuDTO menuDTO){
        log.info("MenuController method editMenu run:");
        ValidationUtils.check(menuDTO, EditGroup.class);
        SysAdminMenuDTO sysAdminMenuDTO = BeanUtil.copyProperties(menuDTO, SysAdminMenuDTO.class);
        int editMenu = adminMenuAppService.editMenu(sysAdminMenuDTO);
        if (editMenu <= 0){
            return ResultData.failResult();
        }
        return ResultData.successResult();
    }

    @RequestMapping("/menu/remove.do")
    public ResultData removeMenu(MenuDTO menuDTO){
        log.info("MenuController method pushMenu run:");
        SysAdminMenuDTO sysAdminMenuDTO = BeanUtil.copyProperties(menuDTO, SysAdminMenuDTO.class);
        adminMenuAppService.removeMenu(sysAdminMenuDTO);
        return ResultData.successResult();
    }

    /**
     * 表单树形结构下拉框
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/menu/loadMenuTree.do")
    public ResultData loadMenuTree() throws JsonProcessingException {
        log.info("MenuController method loadMenuTree run:");
        List<AdminMenuDTO> menuTreeList = adminMenuAppService.loadMenuTree();
        String jsonStr = objectMapper.writeValueAsString(menuTreeList);
        List<AdminMenuVO> listVO = objectMapper.readValue(jsonStr, new TypeReference<List<AdminMenuVO>>() {
        });
        return ResultData.customizeResult(ResultCode.SUCCESS,listVO);
    }


}
