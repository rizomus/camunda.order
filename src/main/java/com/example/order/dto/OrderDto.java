package com.example.order.dto;

import com.example.order.entity.OrderStatus;
import com.example.order.entity.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record OrderDto(
        long id,
        long ownerId,
        String ownerName,
        List<Product> productList,
        boolean prepayment,

        LocalDateTime date,

        OrderStatus status
) {
}
