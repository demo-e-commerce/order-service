package com.example.demo.service.impl;
import com.example.demo.model.order.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Page<Order> getAll(Pageable paging) {
        return orderRepository.findAll(paging);
    }

    @Override
    public Order createOrder(Order order) {
        // call product service
        // call kafka
         return orderRepository.save(order);
    }
}