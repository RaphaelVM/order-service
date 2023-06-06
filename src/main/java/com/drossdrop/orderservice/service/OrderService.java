package com.drossdrop.orderservice.service;

import com.drossdrop.orderservice.dto.OrderRequest;
import com.drossdrop.orderservice.dto.OrderResponse;
import com.drossdrop.orderservice.model.Order;
import com.drossdrop.orderservice.repository.OrderRepository;
import com.drossdrop.orderservice.repository.ProductRepository;
import com.drossdrop.orderservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void createOrder(OrderRequest orderRequest) {
        String productId = orderRequest.getProductId();
//        BigDecimal totalPrice = productRepository.findById(productId).get().getPrice().multiply(BigDecimal.valueOf(orderRequest.getQuantity()));
        BigDecimal totalPrice = BigDecimal.valueOf(orderRequest.getQuantity()*2);

        Order order = Order.builder()
                .productId(productId)
                .userId(orderRequest.getUserId())
                .totalPrice(totalPrice)
                .status("PENDING")
                .build();

        orderRepository.save(order);
        log.info("Order is {} created", order.getId());
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(this::maptoOrderResponse).toList();
    }

    public OrderResponse getOrderById(String id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return maptoOrderResponse(order);
    }

    private OrderResponse maptoOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .productId(order.getProductId())
                .userId(order.getUserId())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .build();
    }
}
