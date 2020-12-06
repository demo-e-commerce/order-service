package com.example.demo.model.product;

import com.example.demo.model.core.AbstractGeneratedIdAuditableTime;
import com.example.demo.model.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@Entity
@Table(name = "order_item")
public class OrderItem extends AbstractGeneratedIdAuditableTime {

    @Column(name = "code")
    @NotBlank(message = "order item code is mandatory")
    private String code;

    @Column(name = "title")
    @NotBlank(message = "order item title is mandatory")
    private String title;

    @Column(name = "description")
    @NotBlank(message = "order item description is mandatory")
    private String description;

    @Column(name = "price")
    @NotBlank(message = "order item price is mandatory")
    private Float price;

    @Column(name = "quantity")
    @NotBlank(message = "order item quantity is mandatory")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
