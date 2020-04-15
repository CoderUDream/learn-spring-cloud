package com.jiang.service.mysql;

import com.jiang.service.mysql.entity.User;
import com.jiang.service.mysql.mapper.UserMapper;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

//@EnableDiscoveryClient
@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(StartApplication.class, args);
    }

}
