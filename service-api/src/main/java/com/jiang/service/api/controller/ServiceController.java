package com.jiang.service.api.controller;

import com.jiang.service.api.config.UserConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private UserConfiguration userConfiguration;

    @RequestMapping("/getSwaggerName")
    public String getSwaggerName() {
        return "service-api:" + name;
    }

    @GetMapping("/getMasterInfo")
    public String getMasterInfo() {
        return userConfiguration.toString();
    }
}
