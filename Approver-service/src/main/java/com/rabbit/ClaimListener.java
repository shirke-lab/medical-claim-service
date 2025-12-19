package com.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.model.ClaimApproval;
import com.repository.ClaimApprovalRepository;
import com.service.ApprovalServiceImpl;

@Service
public class ClaimListener {
	private final ApprovalServiceImpl approvalService;
	private final ClaimApprovalRepository repo;

    public ClaimListener(ClaimApprovalRepository repo, ApprovalServiceImpl approvalService) {
        this.approvalService = approvalService;
		this.repo = repo;
    }

    @RabbitListener(queues = "claim_submit_queue")
    public void receiveClaim(ClaimMessage message) {
	
	

    ClaimApproval approval = new ClaimApproval();
    approval.setClaimId(message.getClaimId());
    approval.setEmployeeId(message.getEmployeeId());
    approval.setStatus("PENDING");

    repo.save(approval);

    System.out.println("Claim received → stored as pending.");
}
}

