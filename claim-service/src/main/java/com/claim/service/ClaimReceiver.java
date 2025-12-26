package com.claim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.claim.model.Claim;

@Service
public class ClaimReceiver {

	
	@RabbitListener(queues = "claim.queue")
	public void receiveClaim(Claim claim) {
	    System.out.println("Received claim: " + claim);
	}
}
