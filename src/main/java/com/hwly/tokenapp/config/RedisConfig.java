package com.hwly.tokenapp.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author libusi
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        // 设置redis连接
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        /*Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);*/

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        // 将redisTemplate的序列化方式更改为StringRedisSerializer
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /*@Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        //使用fastjson序列化
        // value值的序列化采用 StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }*/

}
