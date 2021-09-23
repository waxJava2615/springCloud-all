package com.starry.sky.infrastructure.events.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author wax
 * @description: 事件发布器
 * @date 2021-09-15
 */
@Component
public class DomainEventPublisher {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void publish(ApplicationEvent applicationEvent){
        applicationEventPublisher.publishEvent(applicationEvent);
    }
}
