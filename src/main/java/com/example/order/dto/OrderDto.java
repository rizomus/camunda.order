package com.example.order.dto;

import com.example.order.entity.OrderStatus;
import com.example.order.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        long orderId,
        long ownerId,
        String ownerName,
        List<Product> productList,
        boolean prepayment,

        String marketplace,

        LocalDateTime date,

        OrderStatus status
) {
}
