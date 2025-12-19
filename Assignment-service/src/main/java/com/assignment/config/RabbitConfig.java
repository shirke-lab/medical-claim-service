package com.assignment.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbit.exchange}")
    private String exchange;

    @Value("${rabbit.queue.assignment}")
    private String queueName;

    @Value("${rabbit.routing.assignment}")
    private String routingKey;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue assignmentQueue() {
        return new Queue(queueName);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(assignmentQueue()).to(exchange()).with(routingKey);
    }
}
