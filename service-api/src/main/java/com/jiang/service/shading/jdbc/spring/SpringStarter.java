package com.jiang.service.shading.jdbc.spring;

import com.jiang.service.shading.jdbc.spring.aspectj.AopConfiguration;
import com.jiang.service.shading.jdbc.spring.aspectj.MathCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

/**
 * Created by liyujiang on 2019/12/21.
 */
@Slf4j
public class SpringStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfiguration.class);

//        Kingdom bean = applicationContext.getBean(Kingdom.class);
//        System.out.println(bean);
//        //String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//
//        Person person = applicationContext.getBean(Person.class);
//        System.out.println(person);
//
//        ThirdPartyPerson thirdPartyPerson = applicationContext.getBean(ThirdPartyPerson.class);
//        System.out.println(thirdPartyPerson);
//
//        SpiderMan spiderMan = applicationContext.getBean(SpiderMan.class);
//        System.out.println(spiderMan);
//
//        IronMan ironMan = applicationContext.getBean(IronMan.class);
//        System.out.println(ironMan);
//
//        LightingMan lightingMan = applicationContext.getBean(LightingMan.class);
//        System.out.println(lightingMan);
        //applicationContext.getBean("dog");

        MathCalculator mathCalculator = (MathCalculator) applicationContext.getBean("mathCalculator");
        mathCalculator.div(3, 2);

        applicationContext.publishEvent(new ApplicationEvent(new String("testEvent")) {
        });

        applicationContext.close();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
