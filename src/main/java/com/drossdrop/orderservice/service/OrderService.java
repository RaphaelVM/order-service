package com.drossdrop.orderservice.service;

import com.drossdrop.orderservice.dto.OrderRequest;
import com.drossdrop.orderservice.dto.OrderResponse;
import com.drossdrop.orderservice.model.Order;
import com.drossdrop.orderservice.repository.OrderRepository;
import com.drossdrop.orderservice.repository.ProductRepository;
import com.drossdrop.orderservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    public String createOrder(OrderRequest orderRequest) {
        String productId = orderRequest.getProductId();
        if(productRepository.findById(productId).isEmpty()) {
            return String.format("Product with id %s does not exist", productId);
        }
        BigDecimal totalPrice = productRepository.findById(productId).get().getPrice().multiply(BigDecimal.valueOf(orderRequest.getQuantity()));

        Order order = Order.builder()
                .productId(productId)
                .userId(orderRequest.getUserId())
                .totalPrice(totalPrice)
                .status("PENDING")
                .build();

        orderRepository.save(order);
        LOGGER.info(String.format("Order created...-> %s", order.toString()));
        String stockMessage = checkStock(order.getId(), orderRequest.getQuantity());

        return (String.format(stockMessage+": %s", order.toString()));
    }

    private String checkStock(String id, int quantity) {
        Order order = orderRepository.findById(id).orElseThrow();
        int stock = productRepository.findById(order.getProductId()).get().getStock();
        if (stock >= quantity) {
            updateOrder(id, "APPROVED");
            return "Order approved";
        } else {
            updateOrder(id, "REJECTED");
            return "Order rejected due to insufficient stock";
        }
    }

    private void updateOrder(String id, String status) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
        LOGGER.info(String.format("Order updated...-> %s", order.toString()));
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
