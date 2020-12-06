package com.example.demo.model.product;

import com.example.demo.model.core.AbstractGeneratedIdAuditableTime;
import com.example.demo.model.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "order_item")
public class OrderItem extends AbstractGeneratedIdAuditableTime {

    @Column(name = "code")
    private String code;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price_unit")
    private String priceUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
