package com.via.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

/**
 * @author Qingqing.He
 * @date 2021/1/7 13:36
 * 描述：
 */
@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory){
       //生成redis对象
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        //配置超时时间
        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofSeconds(30));

        RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter,redisCacheConfiguration);
        return redisCacheManager;
    }
}
