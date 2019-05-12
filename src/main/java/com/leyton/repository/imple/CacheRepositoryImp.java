
package com.leyton.repository.imple;

import java.io.Serializable;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Repository;

import com.leyton.repository.inter.CacheRepository;

import reactor.core.publisher.Mono;

@Repository
public class CacheRepositoryImp<T extends Serializable> implements CacheRepository<T> {

    @Value(
            value = "${cache.redis.seconds-to-live:5}")
    private long seconds;

    @Autowired
    private ReactiveValueOperations<String, Object> valueOperation;

    @Override
    public Mono<Boolean> save(String key, T value) {
        return valueOperation.set(key, value, Duration.ofSeconds(seconds));
    }

    @Override
    @SuppressWarnings(
            value = {
                "unchecked"
            })
    public Mono<T> findByKey(String key) {
        return (Mono<T>) valueOperation.get(key);
    }

    @Override
    public Mono<Boolean> deleteByKey(String key) {
        return valueOperation.delete(key);
    }
}
