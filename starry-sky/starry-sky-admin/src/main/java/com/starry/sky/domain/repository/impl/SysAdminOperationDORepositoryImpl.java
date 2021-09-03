package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminOperationDO;
import com.starry.sky.domain.repository.SysAdminOperationDORepository;
import com.starry.sky.infrastructure.orm.repository.SysAdminOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 操作仓储实现类
 */
@Service
public class SysAdminOperationDORepositoryImpl implements SysAdminOperationDORepository{


    @Autowired
    SysAdminOperationRepository sysAdminOperationRepository;
    
    
    @Override
    public List<SysAdminOperationDO> findByOptionId(List<Long> listOperationId) {
        return sysAdminOperationRepository.findByOptionId(listOperationId);
    }
}
