package com.approver.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.approver.dto.ClaimMessage;
import com.approver.model.ClaimIdReceived;
import com.approver.repository.ClaimIdReceivedRepository;
//import com.approver.service.ApprovalServiceImpl;
@EnableRabbit
@Service
public class ClaimListener {
	//private final ApprovalServiceImpl approvalService;
	private final ClaimIdReceivedRepository repo;

	
    public ClaimListener(ClaimIdReceivedRepository repo){//, ApprovalServiceImpl approvalService) {
      //  this.approvalService = approvalService;
		this.repo = repo;
		
    }
//    @Autowired
//    RestTemplate rest;
    @RabbitListener(queues = "approver_queue",
    		containerFactory="rabbitListenerContainerFactory")
        public void readClaim(ClaimMessage message) {
ClaimIdReceived cd= new ClaimIdReceived();
    	cd.setClaimId(message.getClaimId());
    	repo.save(cd);
    	System.out.println("New MESSAGE RECEIVED: " + message.getClaimId());
    	
    	/*
ClaimMessage dto=rest.getForObject( "http://localhost:8081/claims/"+message.getClaimId() , ClaimMessage.class);
    	ClaimdetailsFinal finaldetails=new ClaimdetailsFinal();
    	finaldetails.setClaimId(dto.getClaimId());
    	finaldetails.setEmployeeId(dto.getEmployeeId());
    	finaldetails.setAmount(dto.getAmount());
    	finaldetails.setDescription(dto.getDescription());
    	System.out.println(dto.getClaimId());
    	System.out.println(dto.getAmount());

*/
 //   repo.save(approval);

    System.out.println("Claim received → stored as pending.");
}
}

