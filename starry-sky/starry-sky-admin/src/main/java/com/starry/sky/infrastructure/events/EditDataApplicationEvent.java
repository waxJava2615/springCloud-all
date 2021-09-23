package com.starry.sky.infrastructure.events;

import com.starry.sky.infrastructure.dto.SysAdminDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author wax
 * @description: 事件源
 * @date 2021-09-15
 */
@Getter
@Setter
public class EditDataApplicationEvent extends ApplicationEvent {

    private SysAdminDTO sysAdminDTO;

    public EditDataApplicationEvent(Object source) {
        super(source);
    }
    public EditDataApplicationEvent(Object source,SysAdminDTO sysAdminDTO) {
        super(source);
        this.sysAdminDTO = sysAdminDTO;
    }
}
