package com.jiang.service.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyujiang
 * @date 2019/11/16 14:34
 * @Description
 */
@RestController
@RequestMapping("/service")
public class ServiceController {

    @Value("${swagger.name}")
    private String name;

    @RequestMapping("/getSwaggerName")
    public String getSwaggerName() {
        return "service-api:" + name;
    }
}
