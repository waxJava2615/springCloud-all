package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminOperationDO;
import com.starry.sky.domain.repository.SysAdminOperationDORepository;
import com.starry.sky.infrastructure.constant.StarrySkyAdminLockConstants;
import com.starry.sky.infrastructure.dto.SysAdminOperationDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminOperation;
import com.starry.sky.infrastructure.orm.repository.SysAdminOperationRepository;
import com.starry.sky.infrastructure.utils.assembler.SysAdminOperationAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminOperationCache;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (list == null) {
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_OPERATION_LOCK_NAME +
                    ":findByOptionId", ()->{
                List<SysAdminOperation> listOperation = sysAdminOperationCache.findByOptionId(sysAdminOperationDTO);
                if (listOperation == null) {
                    listOperation =
                            sysAdminOperationRepository.findByOptionId(sysAdminOperationDTO.getListOperationId());
                    if (listOperation == null) {
                        listOperation = new ArrayList<>();
                    }
                    sysAdminOperationCache.findByOptionId(sysAdminOperationDTO, listOperation);
                }
                return listOperation;
            });
        }

        return sysAdminOperationAssembler.poToDOList(list);
    }
}
