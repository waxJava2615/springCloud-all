package com.starry.sky.application.service.impl;

import com.starry.sky.infrastructure.events.publish.DomainEventPublisher;
import com.starry.sky.application.service.SysAdminMenuAppService;
import com.starry.sky.domain.service.SysAdminMenuDomainService;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.utils.assembler.SysAdminMenuAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wax
 * @description: 换取菜单
 * @date 2021-09-13
 */
@Service
public class SysAdminMenuAppServiceImpl implements SysAdminMenuAppService {

    @Autowired
    private SysAdminMenuDomainService sysAdminMenuDomainService;

    @Autowired
    private DomainEventPublisher domainEventPublisher;

    @Autowired
    SysAdminMenuAssembler sysAdminMenuAssembler;

    /**
     * 更新菜单
     *
     * @param sysAdminMenuDTO
     * @return
     */
    @Override
    public boolean updateMenu(SysAdminMenuDTO sysAdminMenuDTO) {
        // 更新菜单时 说明ID 一定存在
        boolean update = sysAdminMenuDomainService.updateMenu(sysAdminMenuDTO);
        //发布事件清除缓存   此处可以替换成MQ
        domainEventPublisher.publish(null);
        return update;
    }
}
