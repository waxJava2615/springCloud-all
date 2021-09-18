package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.domain.repository.SysAdminMenuDORepository;
import com.starry.sky.infrastructure.constant.StarrySkyAdminLockConstants;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;
import com.starry.sky.infrastructure.orm.repository.SysAdminMenuRepository;
import com.starry.sky.infrastructure.utils.assembler.SysAdminMenuAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminMenuCache;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wax
 * @description: 菜单仓储
 * @date 2021-08-20
 */
@Service
public class SysAdminMenuDORepositoryImpl implements SysAdminMenuDORepository {


    @Autowired
    SysAdminMenuRepository sysAdminMenuRepository;

    @Autowired
    SysAdminMenuCache sysAdminMenuCache;

    @Autowired
    SysAdminMenuAssembler sysAdminMenuAssembler;

    @Autowired
    RedissonLockTemplate redissonLockTemplate;


    @Override
    public List<SysAdminMenuDO> findByMenuIdList(SysAdminMenuDTO sysAdminMenuDTO) {
        List<SysAdminMenu> list = sysAdminMenuCache.findByMenuIdList(sysAdminMenuDTO);
        if (list == null) {
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_MENU_LOCK_NAME + ":findByMenuIdList:" + StringUtils.join(sysAdminMenuDTO.getListMenuId(), ","), ()->{
                List<SysAdminMenu> listMenu = sysAdminMenuCache.findByMenuIdList(sysAdminMenuDTO);
                if (listMenu == null) {
                    listMenu = sysAdminMenuRepository.findByMenuIdList(sysAdminMenuDTO.getListMenuId());
                    if (listMenu == null) {
                        listMenu = new ArrayList<>();
                    }
                    sysAdminMenuCache.findByMenuIdList(sysAdminMenuDTO, listMenu);
                }
                return listMenu;
            });
        }
        return sysAdminMenuAssembler.poToDOList(list);
    }


    @Override
    public SysAdminMenuDO findByMenuId(SysAdminMenuDTO sysAdminMenuDTO) {
        SysAdminMenu sysAdminMenu = sysAdminMenuCache.findByMenuId(sysAdminMenuDTO);
        if (sysAdminMenu == null){
            sysAdminMenu = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_MENU_LOCK_NAME + ":findByMenuId:" + sysAdminMenuDTO.getId(), ()->{
                SysAdminMenu sysAdminMenuTemp = sysAdminMenuCache.findByMenuId(sysAdminMenuDTO);
                if (sysAdminMenuTemp == null) {
                    sysAdminMenuTemp = sysAdminMenuRepository.findByMenuId(sysAdminMenuDTO.getId());
                    if (sysAdminMenuTemp == null) {
                        sysAdminMenuTemp = SysAdminMenu.generateDefault();
                    }
                    sysAdminMenuCache.findByMenuId(sysAdminMenuDTO, sysAdminMenuTemp);
                }
                return sysAdminMenuTemp;
            });
        }
        return sysAdminMenuAssembler.poToDO(sysAdminMenu);
    }

    @Override
    public int update(SysAdminMenuDO sysAdminMenuDO){
        SysAdminMenu sysAdminMenu = sysAdminMenuAssembler.doToPO(sysAdminMenuDO);
        return sysAdminMenuRepository.updateByMenuId(sysAdminMenu);
    }

    @Override
    public int delete(List<Long> ids){
        return sysAdminMenuRepository.getBaseMapper().deleteBatchIds(ids);
    }

    @Override
    public int add(SysAdminMenuDO sysAdminMenuDO){
        SysAdminMenu sysAdminMenu = sysAdminMenuAssembler.doToPO(sysAdminMenuDO);
        return sysAdminMenuRepository.getBaseMapper().insert(sysAdminMenu);
    }

}