package com.jiang.service.api.spring.registerbean.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * Created by liyujiang on 2020/2/13.
 */
public class EntityImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.jiang.service.api.spring.registerbean.entity.SpiderMan"};
    }
}
