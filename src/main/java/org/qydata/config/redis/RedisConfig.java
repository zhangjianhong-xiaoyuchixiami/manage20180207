//package org.qydata.config.redis;
//
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Configuration
////@EnableCaching
//@ConfigurationProperties(locations = "classpath:application-test.properties", prefix = "spring.redis")
//@Data
//@Component
//public class RedisConfig extends CachingConfigurerSupport {
////    @Value("${spring.redis.host}")
//    private String host;
////    @Value("${spring.redis.port}")
//    private int port;
////    @Value("${spring.redis.timeout}")
//    private int timeout;
////    @Value("${spring.redis.pool.max-idle}")
//    private int maxIdle;
////    @Value("${spring.redis.pool.max-wait}")
//    private long maxWaitMillis;
//
//    @Bean(name = "jedisPoolConfig")
//    public JedisPool redisPoolFactory() {
//
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
//
//        return jedisPool;
//    }
//
//}
