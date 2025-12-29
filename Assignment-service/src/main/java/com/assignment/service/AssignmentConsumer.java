package com.assignment.service;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.dto.AssignmentMessage;
import com.assignment.model.ClaimAssignment;
import com.assignment.repository.ClaimAssignmentRepository;

@Service
public class AssignmentConsumer {
	 
	    private  final ClaimAssignmentRepository repository ;
		 public AssignmentConsumer(ClaimAssignmentRepository repository ) {
	this.repository=repository;
	} 
	 
	@RabbitListener(
    queues = "claim_assignment_queue",
    containerFactory = "rabbitListenerContainerFactory"
)
public void receiveClaim(AssignmentMessage msg) {

    System.out.println("🔥 MESSAGE RECEIVED: " + msg.getClaimId());

    ClaimAssignment assignment = new ClaimAssignment();
    assignment.setClaimId(msg.getClaimId());
    assignment.setEmployeeId(msg.getEmployeeId());
    assignment.setStatus("pending");
    assignment.setAssignedAt(new Date());

    repository.save(assignment);
}
 
	 
}
