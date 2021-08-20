package com.starry.sky.interfaces.facade;

import com.starry.sky.domain.repository.SysAdminMenuRepository;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-20
 */
@RestController
public class TestController {


    @Autowired
    SysAdminMenuRepository sysAdminMenuRepository;

    @GetMapping("inset")
    public int inset(){
        SysAdminMenu sysAdminMenu = new SysAdminMenu();
        sysAdminMenu.setId(1L);
        sysAdminMenu.setName("测试");
        sysAdminMenu.setUrl("sssss");
        int insert = sysAdminMenuRepository.getBaseMapper().insert(sysAdminMenu);
        return insert;
    }


}
