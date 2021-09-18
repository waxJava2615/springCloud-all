package com.starry.sky.application.event.listener;

import com.starry.sky.application.event.EditDataApplicationEvent;
import com.starry.sky.infrastructure.dto.SysAdminDTO;
import org.springframework.context.ApplicationListener;

/**
 *
 * @author wax
 * @description: 数据变动监听器
 * @date 2021-09-15
 */
public class EditDataListener implements ApplicationListener<EditDataApplicationEvent> {

    @Override
    public void onApplicationEvent(EditDataApplicationEvent event) {
        SysAdminDTO sysAdminDTO = event.getSysAdminDTO();
    }
}
