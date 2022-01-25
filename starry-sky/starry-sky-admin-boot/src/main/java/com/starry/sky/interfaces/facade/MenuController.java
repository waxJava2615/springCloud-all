package com.starry.sky.interfaces.facade;

import com.starry.sky.application.service.SysAdminMenuAppService;
import com.starry.sky.infrastructure.utils.ResultData;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wax
 * @description: 菜单访问入口
 * @date 2021-09-13
 */
@RestController
@RequestMapping("sysAdminMenu")
public class MenuController {

    @Autowired
    private SysAdminMenuAppService sysAdminMenuAppService;


//    /**
//     * 获取菜单列表
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("list.do")
//    public ResultData list(HttpServletRequest request, HttpServletResponse response){
//        int pageNum = 1;
//        int pageSize = 10;
//        List<SysAdminVO> list = menuAppService.list(pageNum,pageSize);
//        return ResultData.customizeResult(ResultCode.SUCCESS.getCode(),
//                ResultCode.SUCCESS.getMessage(), list);
//    }

    @RequestMapping("/edit/updateMenu.do")
    public ResultData updateMenu(SysAdminMenuDTO sysAdminMenuDTO){
        //　参数校验

        boolean updateMenu = sysAdminMenuAppService.updateMenu(sysAdminMenuDTO);
        if (updateMenu){
            ResultData.successResult();
        }
        return ResultData.failResult();
    }



}
