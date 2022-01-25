package com.starry.sky.interfaces.facade;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.starry.sky.application.dto.AdminMenuDTO;
import com.starry.sky.application.dto.AdminUserDTO;
import com.starry.sky.application.rpc.feign.OauthClientService;
import com.starry.sky.application.service.AdminUserAppService;
import com.starry.sky.application.service.SourceRoleAppService;
import com.starry.sky.infrastructure.config.properties.AppSecretProperty;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.dto.SysAdminUserDTO;
import com.starry.sky.infrastructure.utils.ResUtils;
import com.starry.sky.infrastructure.utils.ResultData;
import com.starry.sky.infrastructure.utils.ThreadLocalHolder;
import com.starry.sky.infrastructure.utils.enums.ResultCode;
import com.starry.sky.interfaces.dto.LoginDTO;
import com.starry.sky.interfaces.vo.AdminMenuVO;
import com.starry.sky.interfaces.vo.AdminUserVO;
import com.starry.sky.interfaces.vo.WelComeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @description: 用户控制器
 * @date 2021-11-09
 */
@Slf4j
@RestController
public class UserController {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    OauthClientService oauthClientService;

    @Autowired
    AppSecretProperty appSecretProperty;

    @Autowired
    AdminUserAppService userAdminAppService;

    @Autowired
    SourceRoleAppService sourceRoleAppService;


    @RequestMapping("/login")
    public ResultData login(LoginDTO loginDTO){
        Map<String, String> params = Maps.newHashMap();
        params.put("username",loginDTO.getUsername());
        params.put("password",loginDTO.getPassword());
        params.put("code",loginDTO.getCode());
        params.put("client_id",appSecretProperty.getClientId());
        params.put("client_secret",appSecretProperty.getClientSecret());
        params.put("grant_type",appSecretProperty.getGrantType());
        return oauthClientService.getAccessToken(params);
    }

    @RequestMapping("/refLogin")
    public ResultData refLogin(LoginDTO loginDTO){
        Map<String, String> params = Maps.newHashMap();
        params.put("refresh_token",loginDTO.getRefToken());
        params.put("client_id",appSecretProperty.getClientId());
        params.put("client_secret",appSecretProperty.getClientSecret());
        params.put("grant_type","refresh_token");
        return oauthClientService.getAccessToken(params);
    }

    /**
     * 登录成功后 加载页面信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/loadPage")
    public ResultData loadPage() {
        ThreadLocal<ResUtils> threadLocal = ThreadLocalHolder.getResUtils();
        ResUtils resUtils = threadLocal.get();
        SysAdminUserDTO sysAdminUserDTO = new SysAdminUserDTO();
        sysAdminUserDTO.setId(resUtils.getPayloadUserId());
        sysAdminUserDTO.setAuthoritiesList(resUtils.getPayloadAuthorities());
        // 无需再次处理转换DTO的话就不需要VO了
        AdminUserDTO adminUserDTO = userAdminAppService.loadPage(sysAdminUserDTO);
        // 拆分左侧和顶部菜单
        List<AdminMenuDTO> listMenu = adminUserDTO.getListMenu();
        ListMultimap<Integer, AdminMenuVO> listMultimap = ArrayListMultimap.create();
        listMenu.forEach(l ->listMultimap.put(l.getOption(),BeanUtil.copyProperties(l,AdminMenuVO.class)));
        AdminUserVO adminUserVO = BeanUtil.copyProperties(adminUserDTO, AdminUserVO.class);
        // 转换展示层数据
        WelComeVO welComeVO = new WelComeVO();
        welComeVO.setUserInfo(adminUserVO);
        welComeVO.setLeftMenu(listMultimap.get(CommonConstants.LEFT_MENU_OPTION));
        welComeVO.setTopMenu(listMultimap.get(CommonConstants.TOP_MENU_OPTION));
        return ResultData.customizeResult(ResultCode.SUCCESS,welComeVO);
    }



}
