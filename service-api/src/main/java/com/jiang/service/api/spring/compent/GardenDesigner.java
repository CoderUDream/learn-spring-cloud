package com.jiang.service.api.spring.compent;

import com.jiang.service.api.spring.service.impl.FarmingGarden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Created by liyujiang on 2019/12/23.
 *
 *
 */
@Component
@Slf4j
public class GardenDesigner implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("Kingdom".equalsIgnoreCase(beanName)) {
            ((Kingdom) bean).setGarden(new FarmingGarden());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
