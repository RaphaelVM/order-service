package com.drossdrop.orderservice.rabbitmq;

import com.drossdrop.orderservice.model.Product;
import com.drossdrop.orderservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQConsumer {

    private ProductService productService;

    @RabbitListener(queues = "myQueue")
    public void receiveMessage(String message) {
        log.info("Received Message: " + message);
    }

    @RabbitListener(queues = "create-product-command")
    public void receiveCreateProductCommand(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = null;
        try {
            product = objectMapper.readValue(json, Product.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        productService.createProduct(product);
        log.info("Product is {} created", product);
    }
}