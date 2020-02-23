package com.jiang.service.api.spring.registerbean.beanregistrar;

import com.jiang.service.api.spring.registerbean.entity.IronMan;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by liyujiang on 2020/2/13.
 */
public class EntityBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // importingClassMetadata: 被扫描到有注解的类
        // registry: 用于注册bean的对象
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(IronMan.class);
        builder.addPropertyValue("name", "icon man never die");
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        registry.registerBeanDefinition("registrarIconMan", beanDefinition);
    }
}
