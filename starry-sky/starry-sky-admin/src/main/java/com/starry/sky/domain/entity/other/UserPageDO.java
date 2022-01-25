package com.starry.sky.domain.entity.other;

import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.domain.entity.SysAdminUserDO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author wax
 * @description: 用户页面
 * @date 2021-11-24
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPageDO {

    SysAdminUserDO sysAdminUserDO;

    List<SysAdminMenuDO> listMenu;
}
