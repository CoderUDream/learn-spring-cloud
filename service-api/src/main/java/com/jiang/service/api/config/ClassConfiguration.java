package com.jiang.service.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * @author liyujiang
 * @date 2019/12/09 11:49
 * @Description
 */
@Data
@Configuration
@PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "class")
public class ClassConfiguration {
    private Integer id;
    private String name;
    private String address;

    @PostConstruct
    public void init() {
        System.out.println(toString());
    }
}
