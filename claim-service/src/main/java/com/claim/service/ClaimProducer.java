package com.claim.service;

//import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.claim.dto.ClaimMessage;
import com.claim.model.Claim;

@Service
public class ClaimProducer {
	
	private final RabbitTemplate rabbitTemplate;

    public ClaimProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
	
	@Value("${rabbit.exchange}")
	private String Exchange;

	@Value("${rabbit.routingKey}")
	private String routingKey;
	
	public void sendClaim(Claim claim) {
//		rabbitTemplate.convertAndSend(Exchange, routingKey, claim);
		ClaimMessage msg= new ClaimMessage();
		msg.setClaimId(claim.getClaimId());	
		msg.setEmpId(claim.getEmpid());
		msg.setAmount(claim.getAmount());
		msg.setDescription(claim.getDescription());
	//	msg.setStatus(claim.getStatus());
		msg.setStatus(claim.getStatus().name());

		System.out.println("emp id is - - - >"+msg.getEmpId());
		 rabbitTemplate.convertAndSend("claim.exchange", "claim.routing.key", msg);
		
		System.out.println("Claim published  - " +msg.getClaimId());
		}}


