package com.jiang.service.mysql.service.impl;

import com.jiang.service.mysql.entity.User;
import com.jiang.service.mysql.mapper.UserMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liyujiang
 * @date 2020/04/13 17:37
 * @Description
 */
//@Service
public class MybatisPlusService implements InitializingBean {

    @Autowired
    UserMapper userMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

        System.out.println("---------------");

        System.out.println(userMapper.getById(1));
    }
}
