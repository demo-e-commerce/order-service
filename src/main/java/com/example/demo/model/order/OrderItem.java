package com.example.demo.model.order;

import com.example.demo.controller.dto.OrderItemDto;
import com.example.demo.model.core.AbstractGeneratedIdAuditableTime;
import com.example.demo.model.order.Order;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@Entity
@Table(name = "order_item")
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OrderItem extends AbstractGeneratedIdAuditableTime {

    @Column(name = "code")
    @NotBlank(message = "Order item code is mandatory")
    private String code;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    @NotNull(message = "order item quantity is mandatory")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public OrderItem(OrderItemDto orderItemDto) {
        this.code = orderItemDto.getCode();
        this.title = orderItemDto.getTitle();
        this.description = orderItemDto.getDescription();
        this.price = orderItemDto.getPrice();
        this.quantity = orderItemDto.getQuantity();
    }
}
