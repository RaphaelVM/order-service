package com.drossdrop.orderservice.rabbitmq;

import com.drossdrop.orderservice.model.Product;
import com.drossdrop.orderservice.model.User;
import com.drossdrop.orderservice.service.ProductService;
import com.drossdrop.orderservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @RabbitListener(queues = "product_json")
    public void receiveProduct(Product product) {
        LOGGER.info(String.format("Received message... -> %s", product.toString()));
        productService.createProduct(product);
    }

    @RabbitListener(queues = "user_json")
    public void receiveUser(User user) {
        LOGGER.info(String.format("Received message... -> %s", user.toString()));
        userService.createUser(user);
    }
}