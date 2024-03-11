package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.entity.OrderStatus;
import com.example.order.entity.Product;
import com.example.order.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    static int marker = new Object().hashCode();

    @GetMapping("/test")
    public String test() {
        System.out.println("Order controller test: " + marker);
        return String.valueOf(marker);
    }

    @PostMapping("/test-order")
    public long newOrderTest() {

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(111L, "Cup", 1));
        products.add(new Product(222L, "Ball", 2));
        products.add(new Product(333L, "Book", 1));

        OrderDto dto = orderService.placingOrder(
                1005L,
                "Lenin",
                products,
                true,
                LocalDateTime.now(),
                OrderStatus.NEW
        );
        return dto.orderId();
    }

    @PostMapping("/new")
    public OrderDto newOrder(@RequestBody OrderDto orderDto) {

        OrderDto dto = orderService.placingOrder(
                orderDto.ownerId(),
                orderDto.ownerName(),
                orderDto.productList(),
                orderDto.prepayment(),
                LocalDateTime.now(),
                OrderStatus.NEW
        );
        return dto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable long id) {

        OrderDto dto = null;
        try {
            dto = orderService.getOrder(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/set-order-status")
    public ResponseEntity<String> setStatus(@RequestBody Map<String, String> request) {

        Long id = null;
        String newStatus = null;

        try {
            id = Long.valueOf(request.get("id"));
            newStatus = request.get("newStatus");
            if (id == null || newStatus == null) {
                return new ResponseEntity<String>("The request body must contain the fields 'orderId' and 'newStatus'", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>("The request body must contain the fields 'orderId' and 'newStatus'", HttpStatus.BAD_REQUEST);
        }
        try {
            orderService.setNewStatus(id, OrderStatus.valueOf(newStatus));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>("Wrong status", HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>("ID not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("New status set", HttpStatus.OK);
    }

}
