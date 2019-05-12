
package com.leyton.service.imple;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.leyton.repository.inter.CacheRepository;
import com.leyton.service.inter.CacheService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CacheServiceImp<T extends Serializable> implements CacheService<T> {

    private CacheRepository<T> cacheRepository;

    @Override
    public Mono<Boolean> create(String key, T value) {
        return cacheRepository.save(key, value);
    }

    @Override
    public Mono<T> get(String key) {
        return cacheRepository.findByKey(key);
    }

    @Override
    public Mono<Boolean> update(String key, T value) {
        return cacheRepository.save(key, value);
    }

    @Override
    public Mono<Boolean> delete(String key) {
        return cacheRepository.deleteByKey(key);
    }
}
