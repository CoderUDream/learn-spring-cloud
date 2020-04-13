package com.jiang.learn.eureka.endpoint;

import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by liyujiang on 2019/12/21.
 * 监控eureka
 */
@Component
public class EurekaEndPoint implements ApplicationListener<EurekaRegistryAvailableEvent> {


    @Override
    public void onApplicationEvent(EurekaRegistryAvailableEvent eurekaRegistryAvailableEvent) {
        System.out.println("eureka available");
    }
}
