package com.drossdrop.orderservice.controller;

import com.drossdrop.orderservice.dto.OrderRequest;
import com.drossdrop.orderservice.dto.OrderResponse;
import com.drossdrop.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        String order = orderService.createOrder(orderRequest);
        
        if (order.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product does not exist");
        } else if (order.contains("Order rejected")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order rejected due to insufficient stock");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }
}

