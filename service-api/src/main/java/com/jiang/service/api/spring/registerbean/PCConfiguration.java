package com.jiang.service.api.spring.registerbean;

import com.jiang.service.api.spring.registerbean.condition.LinuxCondition;
import com.jiang.service.api.spring.registerbean.condition.WindowCondition;
import com.jiang.service.api.spring.registerbean.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liyujiang on 2020/2/13.
 */
@Configuration
public class PCConfiguration {

    @Conditional(WindowCondition.class)
    @Bean("bill")
    Person windowCreator() {
        return new Person("比尔盖茨");
    }

    @Conditional(LinuxCondition.class)
    @Bean("linux")
    Person linuxCreator() {
        return new Person("Linux");
    }
}
