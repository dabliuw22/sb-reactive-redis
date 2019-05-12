
package com.leyton.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.RedisSerializationContextBuilder;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Value(
            value = "${spring.redis.host}")
    private String host;

    @Value(
            value = "${spring.redis.port}")
    private int port;

    @Bean
    @Primary
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisSerializationContextBuilder<String, Object> redisSerializationContextBuilder() {
        return RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
    }

    @Bean
    public RedisSerializationContext<String, Object>
            redisSerializationContext(RedisSerializationContextBuilder<String, Object> builder) {
        return builder.value(new GenericJackson2JsonRedisSerializer("_type")).build();
    }

    @Bean
    @Primary
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory,
            RedisSerializationContext<String, Object> serializationContext) {
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }

    @Bean
    @Primary
    public ReactiveStringRedisTemplate
            reactiveStringRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        return new ReactiveStringRedisTemplate(connectionFactory);
    }

    @Bean
    public ReactiveValueOperations<String, Object>
            reactiveValueOperations(ReactiveRedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }
}
