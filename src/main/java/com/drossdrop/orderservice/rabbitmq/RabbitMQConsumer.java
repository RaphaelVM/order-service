package com.drossdrop.orderservice.rabbitmq;

import com.drossdrop.orderservice.model.Product;
import com.drossdrop.orderservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    private ProductService productService;

    @RabbitListener(queues = "product_json")
    public void receiveProduct(Product product) {
        LOGGER.info(String.format("Received message... -> %s", product.toString()));
        productService.createProduct(product);
    }

//    @RabbitListener(queues = "product")
//    public void receiveProduct(String json) {
//        LOGGER.info(String.format("Received message... -> %s", json));
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Product product = null;
//        try {
//            product = objectMapper.readValue(json, Product.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        productService.createProduct(product);
//        log.info("Product is {} created", product);
//    }
}