package com.jiang.service.api.spring.compent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyujiang on 2019/12/24.
 */
@Slf4j
@Component
public class KingdomNameDesigner implements BeanFactoryPostProcessor {

    private static final Map<String, String> nameMap = new HashMap();

    static {
        nameMap.put("name", "Alex");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] definitionNames = beanFactory.getBeanDefinitionNames();
        for (String name : definitionNames) {
            if ("Kingdom".equalsIgnoreCase(name)) {
                BeanDefinition definition = beanFactory.getBeanDefinition(name);
                MutablePropertyValues propertyValues = definition.getPropertyValues();
                propertyValues.addPropertyValues(nameMap);
            }
        }
    }
}
