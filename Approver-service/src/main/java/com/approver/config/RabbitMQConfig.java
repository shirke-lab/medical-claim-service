package com.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${queue.claim.submit:claim_submit_queue}")
    private String claimSubmitQueue;

    @Value("${queue.claim.assigned:claim_assignment_queue}")
    private String claimAssignmentQueue;

    @Value("${exchange.claims:claims.exchange}")
    private String claimsExchange;

    @Bean
    public TopicExchange claimsExchange() {
        return new TopicExchange(claimsExchange);
    }

    @Bean
    public Queue claimSubmitQueue() {
        return new Queue(claimSubmitQueue);
    }

    @Bean
    public Queue claimAssignmentQueue() {
        return new Queue(claimAssignmentQueue);
    }

    @Bean
    public Binding bindingSubmit() {
        return BindingBuilder.bind(claimSubmitQueue()).to(claimsExchange()).with("claim.submitted");
    }

    @Bean
    public Binding bindingAssignment() {
        return BindingBuilder.bind(claimAssignmentQueue()).to(claimsExchange()).with("claim.assigned");
    }
}
