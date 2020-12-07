package com.example.demo.controller.dto;

import com.example.demo.model.order.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class OrderItemDto {
    private String code;

    private Integer quantity;

    private String title;

    private String description;

    private Float price;
}
