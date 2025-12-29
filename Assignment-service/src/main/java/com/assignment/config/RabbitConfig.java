//package com.assignment.config;
/*
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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
    public DirectExchange exchange() {
        return new DirectExchange(exchange);    }
    @Bean
    public Queue assignmentQueue() {
        return new Queue(queueName);    }
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(assignmentQueue()).to(exchange()).with(routingKey);    }
    
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
*/

package com.assignment.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {
	public static final String ASSIGNMENT_QUEUE = "claim_assignment_queue";
    public static final String CLAIM_EXCHANGE = "claim.exchange";
    public static final String CLAIM_ROUTING_KEY = "claim.routing.key";

  //  public static final String CLAIM_QUEUE = "claim.queue";

    @Bean
    public Queue assignmentQueue() {
        return new Queue(ASSIGNMENT_QUEUE, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public DirectExchange claimExchange() {
        return new DirectExchange(CLAIM_EXCHANGE);
    }

    @Bean
    public Binding assignmentBinding(Queue assignmentQueue, DirectExchange claimExchange) {
        return BindingBuilder
                .bind(assignmentQueue)
                .to(claimExchange)
                .with(CLAIM_ROUTING_KEY);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter) {

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
    
    
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter) {

        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }
}
