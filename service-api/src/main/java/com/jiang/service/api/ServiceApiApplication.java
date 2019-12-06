package com.jiang.service.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApiApplication.class, args);
    }

}