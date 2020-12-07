package com.example.demo.service.impl;
import com.example.demo.config.core.Setting;
import com.example.demo.controller.dto.OrderItemDto;
import com.example.demo.controller.exception.CannotCreateOrderException;
import com.example.demo.dto.AuditEvent;
import com.example.demo.model.order.Order;
import com.example.demo.model.order.OrderItem;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.AuditService;
import com.example.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    Setting setting;

    @Autowired
    AuditService auditService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Page<Order> getAll(Pageable paging) {
        return orderRepository.findAll(paging);
    }

    private List<OrderItem> processOrderItems(List<OrderItem> orderItems) {
        List<OrderItemDto> orderItemDtos = orderItems.stream()
                .map(item -> OrderItemDto.builder()
                        .code(item.getCode())
                        .quantity(item.getQuantity()).build())
                .collect(Collectors.toList());

        OrderItemDto[] validOrderItems = restTemplate.postForObject(
                setting.getProcessOrderEndpoint(),
                orderItemDtos,
                OrderItemDto[].class);

        return Arrays.asList(validOrderItems).stream()
                .map(item -> new OrderItem(item))
                .collect(Collectors.toList());
    }

    @Override
    public Order createOrder(Order order) throws CannotCreateOrderException {
        try {
            List<OrderItem> orderItems = processOrderItems(order.getOrderItems());
            order.setOrderItems(orderItems);
            auditService.audit(AuditEvent.builder().userId(order.getUserId()).evenName("create_order").eventDate(Instant.now()).build());
            return orderRepository.save(order);
        } catch (Exception ex) {
            log.error("failed to create order", ex);
            // TODO give detail error to client
            throw new CannotCreateOrderException("failed to create order", ex);
        }
    }
}