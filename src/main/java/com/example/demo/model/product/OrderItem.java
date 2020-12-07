package com.example.demo.model.product;

import com.example.demo.model.core.AbstractGeneratedIdAuditableTime;
import com.example.demo.model.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@Entity
@Table(name = "order_item")
@NoArgsConstructor
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

}
