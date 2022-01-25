package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminOperationDO;
import com.starry.sky.domain.repository.SysAdminOperationDORepository;
import com.starry.sky.infrastructure.constant.AdminLockConstants;
import com.starry.sky.infrastructure.dto.SysAdminOperationDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminOperation;
import com.starry.sky.infrastructure.orm.repository.SysAdminOperationRepository;
import com.starry.sky.infrastructure.utils.assembler.SysAdminOperationAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminOperationCache;
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
 * @version 1.0.0
 * @date 2021/8/29
 * @description 操作仓储实现类
 */
@Service
public class SysAdminOperationDORepositoryImpl implements SysAdminOperationDORepository {


    @Autowired
    SysAdminOperationRepository sysAdminOperationRepository;


    @Autowired
    SysAdminOperationCache sysAdminOperationCache;

    @Autowired
    SysAdminOperationAssembler sysAdminOperationAssembler;

    @Autowired
    RedissonLockTemplate redissonLockTemplate;


    @Override
    public List<SysAdminOperationDO> findByOptionId(SysAdminOperationDTO sysAdminOperationDTO) {
        List<SysAdminOperation> list = sysAdminOperationCache.findByOptionId(sysAdminOperationDTO);
        if (CollectionUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_OPERATION_LOCK_NAME +
                    ":findByOptionId", ()->{
                List<SysAdminOperation> listOperation = sysAdminOperationCache.findByOptionId(sysAdminOperationDTO);
                if (CollectionUtils.isEmpty(listOperation)) {
                    listOperation =
                            sysAdminOperationRepository.findByOptionId(sysAdminOperationDTO.getListOperationId());
                    if (CollectionUtils.isEmpty(listOperation)) {
                        listOperation = new ArrayList<>();
                        listOperation.add(new SysAdminOperation());
                    }
                    sysAdminOperationCache.findByOptionId(sysAdminOperationDTO, listOperation);
                }
                return listOperation;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault ? Collections.emptyList() : sysAdminOperationAssembler.poToDOList(list);
    }


    /**
     * 获取操作集合
     *
     * @param sysAdminOperationDTO
     * @return
     */
    @Override
    public List<SysAdminOperationDO> findByHide(SysAdminOperationDTO sysAdminOperationDTO) {
        List<SysAdminOperation> list = sysAdminOperationCache.findListByHide(sysAdminOperationDTO);
        if (CollectionUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_OPERATION_LOCK_NAME +
                    ":findByHide", ()->{
                List<SysAdminOperation> listOperation = sysAdminOperationCache.findListByHide(sysAdminOperationDTO);
                if (CollectionUtils.isEmpty(listOperation)) {
                    listOperation =
                            sysAdminOperationRepository.findListByHide(sysAdminOperationDTO.getPageNo(),
                                    sysAdminOperationDTO.getPageSize(),sysAdminOperationDTO.getHide());
                    if (CollectionUtils.isEmpty(listOperation)) {
                        listOperation = new ArrayList<>();
                        listOperation.add(new SysAdminOperation());
                    }
                    sysAdminOperationCache.findListByHide(sysAdminOperationDTO, listOperation);
                }
                return listOperation;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault ? Collections.emptyList() : sysAdminOperationAssembler.poToDOList(list);
    }


    /**
     * 根据父id获取操作列表
     *
     * @param sysAdminOperationDTO
     * @return
     */
    @Override
    public List<SysAdminOperationDO> findByParentId(SysAdminOperationDTO sysAdminOperationDTO) {
        return null;
    }
}
