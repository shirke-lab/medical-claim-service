package com.approver.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {
	public static final String APPROVER_QUEUE = "approver_queue";
    public static final String CLAIM_EXCHANGE = "claim.exchange";
    public static final String CLAIM_ROUTING_KEY = "approver.routing.key";
    @Bean
    public DirectExchange claimsExchange() {
        return new DirectExchange(CLAIM_EXCHANGE);    }
    @Bean
    public Queue APPROVER_QUEUE () {
        return new Queue(APPROVER_QUEUE);    }
    @Bean
    public Binding bindingapprove() {
        return BindingBuilder.bind(APPROVER_QUEUE()).to(claimsExchange()).with("approver.routing.key");    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();    }
    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
   }
    
}
