package com.example.demo.controller;

import com.example.demo.controller.dto.RequestPagingDto;
import com.example.demo.model.order.Order;
import com.example.demo.security.User;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Order createOrder(@Valid @RequestBody Order order, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        order.setUserId(user.getId());
        return orderService.createOrder(order);
    }

}
