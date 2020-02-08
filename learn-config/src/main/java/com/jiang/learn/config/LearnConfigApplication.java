package com.jiang.learn.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;


@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class LearnConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnConfigApplication.class, args);
        System.out.println("配置中心Server已启动...");
    }
}
