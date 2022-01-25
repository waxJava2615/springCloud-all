package com.starry.sky.domain.repository.impl;

import cn.hutool.core.bean.BeanUtil;
import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.domain.repository.SysAdminMenuDORepository;
import com.starry.sky.infrastructure.constant.AdminLockConstants;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;
import com.starry.sky.infrastructure.orm.repository.SysAdminMenuRepository;
import com.starry.sky.infrastructure.utils.ArrayerUtils;
import com.starry.sky.infrastructure.utils.Pager;
import com.starry.sky.infrastructure.utils.assembler.SysAdminMenuAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminMenuCache;
import com.starry.sky.infrastructure.utils.cache.generate.CacheTableConstans;
import com.starry.sky.infrastructure.utils.cache.provider.CacheKeyManager;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
import com.starry.sky.infrastructure.utils.validations.SysDefaultValueValidation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    CacheKeyManager cacheKeyManager;


    @Override
    public List<SysAdminMenuDO> findByMenuIdList(SysAdminMenuDTO sysAdminMenuDTO) {
        List<SysAdminMenu> list = sysAdminMenuCache.findByMenuIdList(sysAdminMenuDTO);
        if (ArrayerUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_MENU_LOCK_NAME + ":findByMenuIdList", ()->{
                List<SysAdminMenu> listMenu = sysAdminMenuCache.findByMenuIdList(sysAdminMenuDTO);
                if (ArrayerUtils.isEmpty(listMenu)) {
                    listMenu = sysAdminMenuRepository.findByMenuIdList(sysAdminMenuDTO.getListMenuId());
                    if (ArrayerUtils.isEmpty(listMenu)) {
                        listMenu = new ArrayList<>();
                        listMenu.add(new SysAdminMenu());
                    }
                    sysAdminMenuCache.findByMenuIdList(sysAdminMenuDTO, listMenu);
                }
                return listMenu;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault? Collections.emptyList():sysAdminMenuAssembler.poToDOList(list);
    }


    @Override
    public SysAdminMenuDO findByMenuId(SysAdminMenuDTO sysAdminMenuDTO) {
        SysAdminMenu sysAdminMenu = sysAdminMenuCache.findByMenuId(sysAdminMenuDTO);
        if (sysAdminMenu == null){
            sysAdminMenu = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_MENU_LOCK_NAME + ":findByMenuId", ()->{
                SysAdminMenu sysAdminMenuTemp = sysAdminMenuCache.findByMenuId(sysAdminMenuDTO);
                if (sysAdminMenuTemp == null) {
                    sysAdminMenuTemp = sysAdminMenuRepository.findByMenuId(sysAdminMenuDTO.getId());
                    if (sysAdminMenuTemp == null) {
                        sysAdminMenuTemp = new SysAdminMenu();
                    }
                    sysAdminMenuCache.findByMenuId(sysAdminMenuDTO, sysAdminMenuTemp);
                }
                return sysAdminMenuTemp;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(sysAdminMenu);
        return verifyDefault?null:sysAdminMenuAssembler.poToDO(sysAdminMenu);
    }

    @Override
    public SysAdminMenuDO findByOnlyKey(SysAdminMenuDTO sysAdminMenuDTO) {
        SysAdminMenu sysAdminMenu = sysAdminMenuCache.findByOnlyKey(sysAdminMenuDTO);
        if (sysAdminMenu == null){
            sysAdminMenu = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_MENU_LOCK_NAME + ":findByOnlyKey", ()->{
                SysAdminMenu sysAdminMenuTemp = sysAdminMenuCache.findByOnlyKey(sysAdminMenuDTO);
                if (sysAdminMenuTemp == null) {
                    sysAdminMenuTemp = sysAdminMenuRepository.findByOnlyKey(sysAdminMenuDTO.getOnlyKey());
                    if (sysAdminMenuTemp == null) {
                        sysAdminMenuTemp = new SysAdminMenu();
                    }
                    sysAdminMenuCache.findByOnlyKey(sysAdminMenuDTO, sysAdminMenuTemp);
                }
                return sysAdminMenuTemp;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(sysAdminMenu);
        return verifyDefault?null:sysAdminMenuAssembler.poToDO(sysAdminMenu);
    }

    @Override
    public int update(SysAdminMenuDO sysAdminMenuDO){
        SysAdminMenu sysAdminMenu = sysAdminMenuAssembler.doToPO(sysAdminMenuDO);
        int update = sysAdminMenuRepository.updateByMenuId(sysAdminMenu);
        cacheKeyManager.removeKey(CacheTableConstans.TABLE_SYS_ADMIN_MENU,sysAdminMenuDO.getId().toString());
        return update;
    }


    /**
     * 获取菜单列表
     * @param sysAdminMenuDTO
     * @return
     */
    @Override
    public List<SysAdminMenuDO> findListByHide(SysAdminMenuDTO sysAdminMenuDTO) {
        List<SysAdminMenu> sysAdminMenuList = sysAdminMenuCache.findListByHide(sysAdminMenuDTO);
        if (CollectionUtils.isEmpty(sysAdminMenuList)){
            sysAdminMenuList = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_MENU_LOCK_NAME + ":findListByHide", ()->{
                List<SysAdminMenu> sysAdminMenuTempList = sysAdminMenuCache.findListByHide(sysAdminMenuDTO);
                if (CollectionUtils.isEmpty(sysAdminMenuTempList)) {
                    sysAdminMenuTempList = sysAdminMenuRepository.findListByHide(sysAdminMenuDTO.getPageNo(),
                            sysAdminMenuDTO.getPageSize(), sysAdminMenuDTO.getHide());
                    if (CollectionUtils.isEmpty(sysAdminMenuTempList)) {
                        sysAdminMenuTempList = new ArrayList<>();
                        sysAdminMenuTempList.add(new SysAdminMenu());
                    }
                    sysAdminMenuCache.findListByHide(sysAdminMenuDTO, sysAdminMenuTempList);
                }
                return sysAdminMenuTempList;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(sysAdminMenuList);
        return verifyDefault?null:sysAdminMenuAssembler.poToDOList(sysAdminMenuList);
    }


    /**
     * 后台根据（id 或者 父ID 或者 隐藏显示） 分页查询
     *
     * @param sysAdminMenuDTO
     * @return
     */
    @Override
    public Pager<SysAdminMenuDO> loadMenuList(SysAdminMenuDTO sysAdminMenuDTO) {
        Pager<SysAdminMenu> pagerMenu = sysAdminMenuCache.findMenuList(sysAdminMenuDTO);
        if (pagerMenu == null){
            pagerMenu = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_MENU_LOCK_NAME + ":loadMenuList", ()->{
                Pager<SysAdminMenu> pagerMenuTemp = sysAdminMenuCache.findMenuList(sysAdminMenuDTO);
                if (pagerMenuTemp == null) {
                    pagerMenuTemp = sysAdminMenuRepository.findMenuList(sysAdminMenuDTO.getPageNo(),
                            sysAdminMenuDTO.getPageSize(), sysAdminMenuDTO.getHide(),
                    sysAdminMenuDTO.getId(),sysAdminMenuDTO.getParentId(),sysAdminMenuDTO.getName());
                    if (pagerMenuTemp == null) {
                        pagerMenuTemp = new Pager<>();
                        pagerMenuTemp.defaultPage();
                    }
                    sysAdminMenuCache.findMenuList(sysAdminMenuDTO, pagerMenuTemp);
                }
                return pagerMenuTemp;
            });
        }
        List<SysAdminMenu> menuList = pagerMenu.getList();
        List<SysAdminMenuDO> menuDOList = sysAdminMenuAssembler.poToDOList(menuList);
        Pager<SysAdminMenuDO> pagerMenuDo = new Pager<>();
        long total = pagerMenu.getTotal();
        if (CollectionUtils.isEmpty(menuDOList)){
            pagerMenuDo.setList(Collections.emptyList());
        }else {
            pagerMenuDo.setList(menuDOList);
        }
        pagerMenuDo.setTotal(total);
        pagerMenuDo.setPageNo(sysAdminMenuDTO.getPageNo());
        pagerMenuDo.setPageSize(sysAdminMenuDTO.getPageSize());
        return pagerMenuDo;
    }


    /**
     * 根据ID删除菜单
     * @param sysAdminMenuDTO
     */
    @Override
    public int removeMenu(SysAdminMenuDTO sysAdminMenuDTO) {
        int delete = sysAdminMenuRepository.getBaseMapper().deleteById(sysAdminMenuDTO.getId());
        cacheKeyManager.removeKey(CacheTableConstans.TABLE_SYS_ADMIN_MENU,sysAdminMenuDTO.getId().toString());
        return delete;
    }


    @Override
    public int cusSave(SysAdminMenuDO sysAdminMenuDO) {
        SysAdminMenu sysAdminMenu = BeanUtil.copyProperties(sysAdminMenuDO, SysAdminMenu.class);
        int cusSave = sysAdminMenuRepository.cusSave(sysAdminMenu);
        cacheKeyManager.removeKey(CacheTableConstans.TABLE_SYS_ADMIN_MENU,sysAdminMenuDO.getId().toString());
        return cusSave;
    }
}