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
        System.out.println("we got the cabm in producer");
    }
    public void sendAssignedbyManager(ClaimAssignedbyManager cabm) {
        rabbitTemplate.convertAndSend(exchange, routingKey, cabm);	
        System.out.println("we are pushing this msg to approver service  "+(cabm.getclaimId()));
    }}
