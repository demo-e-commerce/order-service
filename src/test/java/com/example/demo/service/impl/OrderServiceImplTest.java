package com.example.demo.service.impl;

import com.example.demo.model.order.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.AuditService;
import com.example.demo.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.any;

@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @TestConfiguration
    static class OrderServiceImplTestConfig {
        @Bean
        OrderService orderService() {
            return new OrderServiceImpl();
        }
    }

    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    AuditService auditService;

    @Test
    public void testCreateOrder_Success() {
        Order order = Order.builder().userId("").build();
        orderService.createOrder(order);
        Mockito.verify(orderRepository).save(order);
        Mockito.verify(auditService).audit(any());
    }

}
