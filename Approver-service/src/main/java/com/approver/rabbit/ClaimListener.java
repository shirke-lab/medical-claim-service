package com.approver.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.approver.dto.ClaimMessage;
import com.approver.model.ClaimIdReceived;
import com.approver.repository.ClaimIdReceivedRepository;

@EnableRabbit
@Service
public class ClaimListener {
	
	private final ClaimIdReceivedRepository repo;

	
    public ClaimListener(ClaimIdReceivedRepository repo){

		this.repo = repo;
		
    }
    @RabbitListener(queues = "approver_queue",
    		containerFactory="rabbitListenerContainerFactory")
        public void readClaim(ClaimMessage message) {
ClaimIdReceived cd= new ClaimIdReceived();
    	cd.setClaimId(message.getClaimId());
    	repo.save(cd);
    	System.out.println("New MESSAGE RECEIVED: " + message.getClaimId());
    	

    System.out.println("Claim received → stored as pending.");
}
}

