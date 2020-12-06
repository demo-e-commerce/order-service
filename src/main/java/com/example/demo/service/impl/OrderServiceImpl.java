package com.example.demo.service.impl;
import com.example.demo.dto.UserEvent;
import com.example.demo.model.order.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.AuditService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AuditService auditService;

    @Override
    public Page<Order> getAll(Pageable paging) {
        return orderRepository.findAll(paging);
    }

    @Override
    public Order createOrder(Order order) {
        // call product service
        auditService.audit(UserEvent.builder().userId(order.getUserId()).evenName("create_order").eventDate(Instant.now()).build());
        return orderRepository.save(order);
    }
}