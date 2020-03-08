package com.jiang.service.api.redis.service;

/**
 * Created by liyujiang on 2020/3/6.
 */

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 */
public class DistributedLockUtil {
    public static boolean tryLock(RedisTemplate template, String key, String value, Integer timeOut) {
        ValueOperations operations = template.opsForValue();
        Object o = operations.get(key);
        if (Objects.isNull(o)) {
            return operations.setIfAbsent(key, value, timeOut, TimeUnit.MINUTES);
        }

        String holder = (String) o;
        return Objects.equals(holder, value);
    }

    public static boolean tryLock(RedisTemplate template, String key, String value) {
        return tryLock(template, key, value, 24 * 60);
    }

    public static boolean forceUnLock(RedisTemplate template, String key) {
        ValueOperations operations = template.opsForValue();
        return template.delete(key);
    }

    public static boolean unLock(RedisTemplate template, String key, String value) {
        ValueOperations operations = template.opsForValue();
        Object o = operations.get(key);
        if (Objects.isNull(o)) {
            return true;
        }

        String holder = (String) o;
        if (Objects.equals(holder, value)) {
            return template.delete(key);
        }

        return false;
    }
}
