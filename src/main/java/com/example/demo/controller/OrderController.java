package com.example.demo.controller;

import com.example.demo.controller.dto.RequestPagingDto;
import com.example.demo.model.order.Order;
import com.example.demo.security.User;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public Page<Order> getAll(RequestPagingDto pagingDto) {
        return orderService.getAll(pagingDto.getPageable());
    }

    @PostMapping
    public Order createOrder(Order order, Principal principal) {
        User user = (User) principal;
        order.setCustomerId(user.getId());
        return orderService.createOrder(order);
    }

}
