package com.jiang.service.shading.jdbc.spring.event;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by liyujiang on 2020/2/16.
 */
@Slf4j
public class ApplicationEventTest implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info(event.toString());
    }
}
