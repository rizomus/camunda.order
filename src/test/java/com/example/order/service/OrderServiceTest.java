//package com.example.order.service;
//
//import com.example.order.dto.OrderDto;
//import com.example.order.entity.OrderStatus;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest()
//class OrderServiceTest {
//    @InjectMocks
//    OrderService orderService;
//
//    @Test
//    void placingOrderTest() {
//        OrderDto dto = orderService.placingOrder(1L,
//                "Vasya",
//                List.of(111L, 222L, 333L),
//                true,
//                OrderStatus.NEW
//        );
//        assertNotNull(dto.orderId());
//    }
//}
