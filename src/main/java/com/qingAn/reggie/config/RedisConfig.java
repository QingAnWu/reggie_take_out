package com.qingAn.reggie.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //修改key的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //修改默认value的jdk序列器修改为json序列化器,效果是存储到redis服务器中是json字符串格式
        redisTemplate.setValueSerializer(new GenericFastJsonRedisSerializer());

        return redisTemplate;
    }
}