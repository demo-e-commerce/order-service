package com.example.demo.model.order;

import com.example.demo.model.core.AbstractGeneratedIdAuditableTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter @Setter
@Entity
@Builder
@Table(name = "user_order")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends AbstractGeneratedIdAuditableTime {

    @Column(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userId;

    @Column(name = "status")
    private OrderStatus status = OrderStatus.NEW;

    @NotEmpty(message = "order should have at least 1 items")
    @Valid
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, orphanRemoval = true)
    List<OrderItem> orderItems;
}
