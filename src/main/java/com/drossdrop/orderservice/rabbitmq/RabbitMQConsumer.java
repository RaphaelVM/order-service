package com.drossdrop.orderservice.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = "myQueue")
    public void receiveMessage(String message) {
        log.info("Received Message: " + message);
    }
}