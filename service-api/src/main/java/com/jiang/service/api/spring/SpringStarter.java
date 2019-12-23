package com.jiang.service.api.spring;

import com.jiang.service.api.spring.config.SpringStartConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

/**
 * Created by liyujiang on 2019/12/21.
 */
@Slf4j
public class SpringStarter {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringStartConfig.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            //log.info(name);
        }

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
