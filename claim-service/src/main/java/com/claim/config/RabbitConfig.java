package com.claim.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;


@Configuration
public class RabbitConfig {

    public static final String CLAIM_QUEUE = "claim.queue";
    public static final String CLAIM_EXCHANGE = "claim.exchange";
    public static final String CLAIM_ROUTING_KEY = "claim.routing.key";

    @Bean
    public Queue claimQueue() {
        return new Queue(CLAIM_QUEUE, true); // durable
    }

    @Bean
    public DirectExchange claimExchange() {
        return new DirectExchange(CLAIM_EXCHANGE);
    }

    @Bean
    public Binding claimBinding(Queue claimQueue, DirectExchange claimExchange) {
        return BindingBuilder.bind(claimQueue).to(claimExchange).with(CLAIM_ROUTING_KEY);
    }
 
	
	
	@Bean
	public  Jackson2JsonMessageConverter  jasonMessageConvertor() {
		return new  Jackson2JsonMessageConverter ();
	}
	
//	 @Bean
//	    public RabbitTemplate rabbitTemplate(
//	            ConnectionFactory connectionFactory,
//	            Jackson2JsonMessageConverter converter) {
//
//	        RabbitTemplate template = new RabbitTemplate(ConnectionFactory);
//	        template.setMessageConverter(converter);
//	        return template;
//	    }
//	}

@Bean
public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                     Jackson2JsonMessageConverter converter) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(converter);
    return template;


}}
