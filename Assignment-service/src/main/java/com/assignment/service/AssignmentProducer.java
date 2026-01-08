package com.assignment.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.assignment.dto.AssignmentMessage;
import com.assignment.dto.ClaimAssignedToApprover;
import com.assignment.model.ClaimAssignedbyManager;

@Service
public class AssignmentProducer {

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbit.exchange}") private String exchange;
    @Value("${rabbit.routing.assignment}") private String routingKey;
    public AssignmentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendAssignedbyManager(ClaimAssignedToApprover msg) {
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);		
    }}
