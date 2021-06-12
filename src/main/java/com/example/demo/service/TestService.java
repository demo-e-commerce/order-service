package com.example.demo.service;

import org.springframework.cache.annotation.Cacheable;

public interface TestService {
    @Cacheable("sum")
    Integer plus(Integer a, Integer b);
}
