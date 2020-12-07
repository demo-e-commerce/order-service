package com.example.demo.controller.dto;

import com.example.demo.model.order.OrderItem;
import lombok.*;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class OrderItemDto {
    private String code;

    private Integer quantity;

    private String title;

    private String description;

    private Float price;
}
