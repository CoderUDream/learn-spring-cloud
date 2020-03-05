package com.jiang.service.api.redis.service;

import com.jiang.service.api.redis.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyujiang on 2020/3/5.
 */
@Slf4j
@Component
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @PostConstruct
    void test() {
        redisOpertions();
    }

    private void redisOpertions() {
        stringOperations();
        listOperations();
    }

    /**
     * 对String数据的操作
     */
    void stringOperations() {
        // 设值
        // 设置简单的key-value
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set("jiang", 1);

        // 设置key-object
        Student stu = new Student("tom", 20);
        operations.set("tom", stu);

        // 批量设置
        Map<String, String> map = new HashMap<>();
        map.put("wang", "teacher");
        map.put("li", "doctor");
        operations.multiSet(map);

        // 根据是否存在设置
        operations.setIfPresent("zhang", "diver");
        operations.setIfAbsent("zhang", "diver");

        // 取值


    }

    /**
     * 对list数据的操作
     */
    void listOperations() {
        ListOperations operations = redisTemplate.opsForList();
        redisTemplate.delete("list");

        // 设值

        // 在头的位置插入 LPUSH
        operations.leftPush("list", "two");
        // 在头的位置插入 LPUSH
        operations.leftPush("list", "one");
        // 在末尾的位置插入 RPUSH
        operations.rightPush("list", "three");

        // 取值

        // 范围取值
        List list = operations.range("list", 0, -1);
        log.info(list.toString());

        // 头部出队列
        String element = (String) operations.leftPop("list");
        log.info(element);

        // 尾部出队列
        element = (String) operations.rightPop("list");
        log.info(element);
    }
}
