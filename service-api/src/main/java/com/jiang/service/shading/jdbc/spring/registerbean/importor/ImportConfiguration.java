package com.jiang.service.shading.jdbc.spring.registerbean.importor;

import com.jiang.service.shading.jdbc.spring.registerbean.beanregistrar.EntityBeanDefinitionRegistrar;
import com.jiang.service.shading.jdbc.spring.registerbean.entity.ThirdPartyPerson;
import com.jiang.service.shading.jdbc.spring.registerbean.factorybean.EntityFactoryBean;
import com.jiang.service.shading.jdbc.spring.registerbean.importselector.EntityImportSelector;
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
