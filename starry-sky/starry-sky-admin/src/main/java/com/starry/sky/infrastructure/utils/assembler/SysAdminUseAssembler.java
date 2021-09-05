package com.starry.sky.infrastructure.utils.assembler;

import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/4
 * @description 数据转换
 */

public interface SysAdminUseAssembler {


    public SysAdminUserDO toDO(SysAdminUser sysAdminUser);
    
    
    
    public SysAdminUser toPO(SysAdminUserDO sysAdminUserDO);

}
