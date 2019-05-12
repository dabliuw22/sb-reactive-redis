
package com.leyton.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leyton.dto.Person;
import com.leyton.service.inter.CacheService;
import com.leyton.util.CacheUtil;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(
        value = {
            "/cache/persons"
        })
@AllArgsConstructor
public class CacheController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);

    private CacheService<Person> personCacheService;

    @PostMapping
    public Mono<Boolean> create(@RequestBody Person person) {
        String key = CacheUtil.generateKey();
        person.setKey(key);
        LOGGER.info("Request: [{}]...", person);
        return personCacheService.create(key, person);
    }

    @GetMapping
    public Mono<Person> get(@RequestParam(
            value = "key") String key) {
        return personCacheService.get(key);
    }

    @DeleteMapping
    public Mono<Boolean> delete(@RequestParam(
            value = "key") String key) {
        return personCacheService.delete(key);
    }

    @PutMapping
    public Mono<Boolean> update(@RequestParam(
            value = "key") String key, @RequestBody Person person) {
        return personCacheService.update(key, person);
    }
}
