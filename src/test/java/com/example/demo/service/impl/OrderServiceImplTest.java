package com.example.demo.service.impl;

import com.example.demo.config.core.Setting;
import com.example.demo.controller.dto.OrderItemDto;
import com.example.demo.controller.exception.CannotCreateOrderException;
import com.example.demo.model.order.Order;
import com.example.demo.model.order.OrderItem;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.AuditService;
import com.example.demo.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    Setting setting;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    AuditService auditService;

    @MockBean
    RestTemplate restTemplate;

    @Test
    public void testCreateOrder_Success() throws CannotCreateOrderException {
        List<OrderItem> orderItems = Arrays.asList(
                OrderItem.builder().code("1").quantity(1).build(),
                OrderItem.builder().code("").quantity(1).build()
        );
        Order order = Order.builder().userId("").orderItems(orderItems).build();
        OrderItemDto[] orderItemDtos = {
                OrderItemDto.builder().build(),
                OrderItemDto.builder().build(),
        };

        when(setting.getProcessOrderEndpoint()).thenReturn("");
        when(restTemplate.postForObject(
                anyString(),
                any(),
                any())).thenReturn(orderItemDtos);
        when(orderRepository.save(any())).thenReturn(order);
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);

        orderService.createOrder(order);

        verify(orderRepository).save(orderArgumentCaptor.capture());
        assertThat(orderArgumentCaptor.getValue().getOrderItems()).hasSize(2);
        verify(orderRepository).save(order);
        verify(auditService).audit(any());
    }

    @Test(expected = CannotCreateOrderException.class)
    public void testCreateOrder_Failed() throws CannotCreateOrderException {
        List<OrderItem> orderItems = Arrays.asList(
                OrderItem.builder().code("1").quantity(1).build(),
                OrderItem.builder().code("").quantity(1).build()
        );
        Order order = Order.builder().userId("").orderItems(orderItems).build();

        when(setting.getProcessOrderEndpoint()).thenReturn("");
        doThrow(new RuntimeException())
                .when(restTemplate)
                .postForObject(
                anyString(),
                any(),
                any());

        orderService.createOrder(order);
    }

}
