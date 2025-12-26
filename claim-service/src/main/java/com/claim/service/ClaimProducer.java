package com.claim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.claim.model.Claim;

@Service
public class ClaimProducer {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${rabbit.exchange}")
	private String Exchange;

	@Value("${rabbit.routingKey}")
	private String routingKey;
	
	public void sendClaim(Claim claim) {
//		rabbitTemplate.convertAndSend(Exchange, routingKey, claim);
		rabbitTemplate.convertAndSend("claim.queue", claim);
		System.out.println("Claim published" +claim.getClaimId());
	}
}

