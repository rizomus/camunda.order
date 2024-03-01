package com.example.order.entity;

import com.example.order.dto.OrderDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long ownerId;

    private String ownerName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Product> products;

    private boolean prepayment;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    public OrderDto getDto() {
        return new OrderDto(id, ownerId, ownerName, products, prepayment, date, status);
    }

}
