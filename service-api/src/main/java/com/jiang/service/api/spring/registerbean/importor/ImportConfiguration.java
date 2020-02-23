package com.jiang.service.api.spring.registerbean.importor;

import com.jiang.service.api.spring.registerbean.beanregistrar.EntityBeanDefinitionRegistrar;
import com.jiang.service.api.spring.registerbean.importselector.EntityImportSelector;
import com.jiang.service.api.spring.registerbean.entity.ThirdPartyPerson;
import com.jiang.service.api.spring.registerbean.factorybean.EntityFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by liyujiang on 2020/2/13.
 */
@Configuration
@Import({ThirdPartyPerson.class,
        EntityImportSelector.class,
        EntityBeanDefinitionRegistrar.class,
        EntityFactoryBean.class})
public class ImportConfiguration {
}
