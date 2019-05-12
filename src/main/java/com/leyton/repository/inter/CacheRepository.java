
package com.leyton.repository.inter;

import reactor.core.publisher.Mono;

public interface CacheRepository<T> {

    Mono<Boolean> save(String key, T value);

    Mono<T> findByKey(String key);

    Mono<Boolean> deleteByKey(String key);
}
