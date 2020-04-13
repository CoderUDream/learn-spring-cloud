package com.jiang.service.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.jiang.service.api.hystrix"})
public class ServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApiApplication.class, args);
    }

}
