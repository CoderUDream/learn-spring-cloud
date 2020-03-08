package com.jiang.service.api.redis.service;

import com.jiang.service.api.redis.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Created by liyujiang on 2020/3/5.
 */
@Slf4j
@Component
public class RedisTest implements InitializingBean {

    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("clusterRedisTemplate")
    RedisTemplate clusterRedisTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        redisClusterOperations();
        //redisOperations();
    }

    private void redisClusterOperations() {
        ValueOperations operations = clusterRedisTemplate.opsForValue();
        operations.set("cluster-1", "1");
        operations.set("cluster-2", "2");
        operations.set("cluster-3", "3");
        operations.set("cluster-4", "4");
        operations.set("cluster-5", "5");
        operations.set("cluster-6", "6");
    }

    private void redisOperations() {
        stringOperations();
        listOperations();
        testDistributedLock();
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

    static volatile int ticket = 100;
    static volatile int count = 0;

    void testWithoutDistributedLock() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    if (ticket <= 0) {
                        return;
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    ticket--;
                    log.info(String.valueOf(ticket));
                    count++;

                }
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("finish, ticket is:" + ticket + ", count:" + count);
    }

    void testDistributedLock() {
        ReentrantLock lock = new ReentrantLock();
        Condition finish = lock.newCondition();
        String ticketLock = "TICKET_LOCK";

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    if (!DistributedLockUtil.tryLock(redisTemplate, ticketLock, Thread.currentThread().getName())) {
                        continue;
                    }

                    try {
                        if (ticket <= 0) {
                            return;
                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        ticket--;
                        log.info(String.valueOf(ticket));
                        count++;
                    } finally {
                        DistributedLockUtil.unLock(redisTemplate, ticketLock, Thread.currentThread().getName());
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("finish, ticket is:" + ticket);
    }
}
