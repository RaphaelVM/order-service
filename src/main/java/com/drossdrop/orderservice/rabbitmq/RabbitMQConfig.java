package com.drossdrop.orderservice.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RabbitMQConfig {

    @Bean
    public Queue productQueue() {
        return new Queue("product");
    }

    @Bean
    public Queue productJsonQueue() {
        return new Queue("product_json");
    }

    @Bean
    public Queue userJsonQueue() { return new Queue("user_json"); }

    @Bean
    public TopicExchange productExchange() { return new TopicExchange("product_exchange");}

    @Bean
    public TopicExchange userExchange() { return new TopicExchange("user_exchange");}

    @Bean
    public Binding productBinding() {
        return BindingBuilder
                .bind(productQueue())
                .to(productExchange())
                .with("product_routing_key");
    }

    @Bean
    public Binding productJsonBinding() {
        return BindingBuilder
                .bind(productJsonQueue())
                .to(productExchange())
                .with("product_routing_json_key");
    }

    @Bean
    public Binding userJsonBinding() {
        return BindingBuilder
                .bind(userJsonQueue())
                .to(userExchange())
                .with("user_routing_json_key");
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}