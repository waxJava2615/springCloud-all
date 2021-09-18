package com.starry.sky.domain.service.impl;

import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.domain.repository.SysAdminMenuDORepository;
import com.starry.sky.domain.service.SysAdminMenuDomainService;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.utils.assembler.SysAdminMenuAssembler;
import com.starry.sky.infrastructure.vo.SysAdminMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @description: 菜单实现类
 * @date 2021-09-16
 */
@Service
public class SysAdminMenuDomainServiceImpl implements SysAdminMenuDomainService {


    @Autowired
    private SysAdminMenuDORepository sysAdminMenuDORepository;

    @Autowired
    private SysAdminMenuAssembler sysAdminMenuAssembler;


    /**
     * 更新菜单
     * @param sysAdminMenuDTO
     * @return
     */
    @Override
    public boolean updateMenu(SysAdminMenuDTO sysAdminMenuDTO) {
        SysAdminMenuDO sysAdminMenuDO = sysAdminMenuAssembler.dtoToDO(sysAdminMenuDTO);
        int update = sysAdminMenuDORepository.update(sysAdminMenuDO);
        return update > 0? Boolean.TRUE : Boolean.FALSE;
    }



    @Override
    public SysAdminMenuVO findById(SysAdminMenuDTO sysAdminMenuDTO){
        List<SysAdminMenuDO> list = sysAdminMenuDORepository.findByMenuId(sysAdminMenuDTO);
        return null;
    }


}
