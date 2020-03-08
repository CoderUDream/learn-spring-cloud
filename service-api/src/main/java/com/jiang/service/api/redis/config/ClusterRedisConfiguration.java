package com.jiang.service.api.redis.config;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by liyujiang on 2020/3/7.
 */

@Configuration
public class ClusterRedisConfiguration {

    @Value("${spring.redis.timeout:3000}")
    private int timeout;
    @Value("${spring.redis.jedis.pool.max-active:500}")
    private int maxActive;
    @Value("${spring.redis.jedis.pool.max-wait:-1}")
    private long maxWaitMillis;
    @Value("${spring.redis.jedis.pool.max-idle:100}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle:100}")
    private int minIdle;

    @Autowired
    ClusterConfigurationProperties clusterProperties;

    @Bean("clusterConnectionFactory")
    RedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory(
                new org.springframework.data.redis.connection.RedisClusterConfiguration(clusterProperties.getNodes()));
    }

    @Bean(name = "clusterRedisTemplate")
    public RedisTemplate<String, Serializable> redisTemplate(@Autowired @Qualifier("clusterConnectionFactory")
                                                                     RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
