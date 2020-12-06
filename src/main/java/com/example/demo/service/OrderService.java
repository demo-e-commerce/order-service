package com.example.demo.service;

import com.example.demo.model.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Order> getAll(Pageable paging);

    Order createOrder(Order order);
}
