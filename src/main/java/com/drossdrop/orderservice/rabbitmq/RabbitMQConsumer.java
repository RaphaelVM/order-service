package com.drossdrop.orderservice.rabbitmq;

import com.drossdrop.orderservice.model.Product;
import com.drossdrop.orderservice.service.ProductService;
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
    public void receiveCreateProductCommand(Product product) {
        productService.createProduct(product);
        log.info("Product is {} created", product);
    }
}