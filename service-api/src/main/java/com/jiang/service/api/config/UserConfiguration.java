package com.jiang.service.api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author liyujiang
 * @date 2019/12/06 14:26
 * @Description
 */
@Data
@Configuration
@PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
public class UserConfiguration {

    @Resource
    Environment environment;

    @Value("${master.name}")
    private String userName;

    @Value("${master.age}")
    private Integer age;

    @PostConstruct
    public void testBean() {
        String nameProp = environment.getProperty("master.name");
        String ageProp = environment.getProperty("master.age");
        System.out.println("nameProp = " + nameProp + ", ageProp=" + ageProp);
    }
}
