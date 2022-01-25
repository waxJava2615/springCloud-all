package com.starry.sky.interfaces.facade;

import com.starry.sky.infrastructure.utils.ResultData;
import com.starry.sky.interfaces.dubbo.IUserAdminService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-11
 */
@RestController
public class DubboController {

    @DubboReference(version = "1.0.0",group = "starry-sky-admin",interfaceClass = IUserAdminService.class)
    IUserAdminService iUserAdminService;

    @RequestMapping("/hello/dubbo")
    public ResultData hello(){
//        UserDTO userDTO = iUserAdminService.loadByUserName("wax");
        return ResultData.customizeResult(0,"成功","wax");
    }
}
