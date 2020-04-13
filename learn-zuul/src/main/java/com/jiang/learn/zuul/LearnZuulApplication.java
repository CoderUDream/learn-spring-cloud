package com.jiang.learn.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class LearnZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnZuulApplication.class, args);
        System.out.println("网关Server已启动...");
    }

}
