
package com.leyton.service.inter;

import reactor.core.publisher.Mono;

public interface CacheService<T> {

    Mono<Boolean> create(String key, T value);

    Mono<T> get(String key);

    Mono<Boolean> update(String key, T value);

    Mono<Boolean> delete(String key);
}
