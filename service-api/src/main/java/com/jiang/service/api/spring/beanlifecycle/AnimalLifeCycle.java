package com.jiang.service.api.spring.beanlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.Objects;

/**
 * Created by liyujiang on 2020/2/13.
 */
@Slf4j
@Configuration
public class AnimalLifeCycle {

    class Dog implements InitializingBean, DisposableBean {

        public Dog() {
            log.info("dog constructor...");
        }

        @PostConstruct
        public void postConstruct() {
            log.info("dog postConstruct...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            log.info("dog afterPropertiesSet...");
        }



        public void init() {
            log.info("dog method init...");
        }

        @PreDestroy
        public void preDestroy() {
            log.info("dog preDestroy...");
        }

        @Override
        public void destroy() throws Exception {
            log.info("dog DisposableBean destroy...");
        }

        public void destroyMethod() {
            log.info("dog method destroy...");
        }
    }

    class Cat implements InitializingBean, DisposableBean {

        public Cat() {
            log.info("cat constructor...");
        }

        @Override
        public void destroy() throws Exception {
            log.info("cat destory...");

        }

        @Override
        public void afterPropertiesSet() throws Exception {
            log.info("cat afterPropertiesSet...");
        }
    }

    class Sheep {

        private String name;

        public Sheep() {
            log.info("sheep constructor...");
        }

        /**
         * 和InitializingBean一样，所有的属性都设置好了后，调用
         */
        @PostConstruct
        public void init() {
            log.info("sheep postConstruct...");
        }

        /**
         * 容器销毁前调用
         */
        @PreDestroy
        public void destroy() {
            log.info("sheep preDestroy...");
        }
    }

    class Tiger implements BeanPostProcessor {
        public Tiger() {
            log.info("tiger constructor...");
        }

        @PostConstruct
        public void init() {
            log.info("tiger postConstruct...");
        }

        /**
         * 容器销毁前调用
         */
        @PreDestroy
        public void destroy() {
            log.info("tiger preDestroy...");
        }


        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (Objects.equals(beanName, "dog")) {
                log.info(beanName + " postProcessBeforeInitialization");
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (Objects.equals(beanName, "dog")) {
                log.info(beanName + " postProcessAfterInitialization");
            }
            return bean;
        }

        /**
         * sheep constructor...
         * sheep postProcessBeforeInitialization...
         * sheep postConstruct...
         * sheep postProcessAfterInitialization...
         * sheep preDestory...
         */
    }

    @Bean(name = "dog", initMethod = "init", destroyMethod = "destroyMethod")
    Dog getDogBean() {
        return new Dog();
    }

    @Bean
    Cat getCatBean() {
        return new Cat();
    }

    @Bean(name = "sheep")
    Sheep getSheepBean() {
        return new Sheep();
    }

    @Bean
    Tiger getTigerBean() {
        return new Tiger();
    }
}
