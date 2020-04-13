package com.jiang.service.api.spring.aspectj;

import com.jiang.service.api.spring.event.ApplicationEventTest;
import com.jiang.service.api.spring.registerbean.factorybean.EntityFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by liyujiang on 2020/2/14.
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfiguration {

    @Bean
    MathCalculator mathCalculator() {
        return new MathCalculatorImpl();
    }

    @Bean
    LogAspectJ logAspectJ() {
        return new LogAspectJ();
    }

    @Bean
    EntityFactoryBean entityFactoryBean() {
        return new EntityFactoryBean();
    }

    @Bean
    ApplicationEventTest applicationEventTest() {
        return new ApplicationEventTest();
    }
}
