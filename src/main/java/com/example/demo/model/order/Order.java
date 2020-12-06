package com.example.demo.model.order;

import com.example.demo.model.core.AbstractGeneratedIdAuditableTime;
import com.example.demo.model.product.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "user_order")
public class Order extends AbstractGeneratedIdAuditableTime {

    @Column(name = "code")
    private String code;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "status")
    private OrderStatus status;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, orphanRemoval = true)
    List<OrderItem> orderItems;
}
