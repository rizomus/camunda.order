package com.example.order.service;

import com.example.order.dto.OrderDto;
import com.example.order.entity.Order;
import com.example.order.entity.OrderStatus;
import com.example.order.entity.Product;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void setNewStatus(Long id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id).get();
        order.setStatus(newStatus);
        orderRepository.save(order);
    }

    @Transactional
    public OrderDto placingOrder(
                                 long ownerId,
                                 String ownerName,
                                 List<Product> productList,
                                 boolean prepayment,
                                 String marketplace,
                                 LocalDateTime date,
                                 OrderStatus status) {

        Order order = Order.builder()
                .ownerId(ownerId)
                .ownerName(ownerName)
                .products(productList)
                .prepayment(prepayment)
                .marketplace(marketplace)
                .date(date)
                .status(status)
                .build();

        orderRepository.save(order);

        return order.getDto();
    }

    @Transactional
    public OrderDto getOrder(long id) {

        Order order = orderRepository.getReferenceById(id);
        return order.getDto();
    }
}
