package com.approver.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.approver.dto.ClaimMessage;
import com.approver.model.ClaimDecision;
import com.approver.repository.ClaimDecisionRepository;
import com.approver.service.ApprovalServiceImpl;
@EnableRabbit
@Service
public class ClaimListener {
	private final ApprovalServiceImpl approvalService;
	private final ClaimDecisionRepository repo;

    public ClaimListener(ClaimDecisionRepository repo, ApprovalServiceImpl approvalService) {
        this.approvalService = approvalService;
		this.repo = repo;
    }

    @RabbitListener(queues = "approver_queue",
    		containerFactory="rabbitListenerContainerFactory")
    
    public void readClaim(ClaimMessage message) {
	
    	System.out.println("New MESSAGE RECEIVED: " + message.getClaimId());

    ClaimDecision approval = new ClaimDecision();
    approval.setClaimId(message.getClaimId());
    //approval.setEmployeeId(message.getEmployeeId());
    approval.setStatus("PENDING");

    //repo.save(approval);

    System.out.println("Claim received → stored as pending.");
}
}

