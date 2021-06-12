package com.example.demo.service.impl;

import com.example.demo.service.TestService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    @Cacheable("sum")
    public Integer plus(Integer a, Integer b) {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a + b;
    }
}
