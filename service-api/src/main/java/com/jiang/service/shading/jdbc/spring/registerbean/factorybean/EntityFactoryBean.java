package com.jiang.service.shading.jdbc.spring.registerbean.factorybean;

import com.jiang.service.shading.jdbc.spring.registerbean.entity.LightingMan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by liyujiang on 2020/2/13.
 */
@Slf4j
public class EntityFactoryBean implements FactoryBean<LightingMan> {

    public EntityFactoryBean() {
        log.info("EntityFactoryBean...constructor...");
    }

    @Override
    public LightingMan getObject() throws Exception {
        return new LightingMan("jiang");
    }

    @Override
    public Class<?> getObjectType() {
        return LightingMan.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
