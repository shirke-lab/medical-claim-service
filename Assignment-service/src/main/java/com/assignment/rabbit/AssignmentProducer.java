package com.assignment.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.assignment.dto.AssignmentMessage;

@Service
public class AssignmentProducer {

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbit.exchange}") private String exchange;
    @Value("${rabbit.routing.assignment}") private String routingKey;

    public AssignmentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendAssignment(AssignmentMessage msg) {
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
    }
}
