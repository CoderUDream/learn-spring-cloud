package com.jiang.service.api.spring;

import com.jiang.service.api.spring.compent.Kingdom;
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

        Kingdom bean = applicationContext.getBean(Kingdom.class);
        System.out.println(bean);
        //String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();


        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
