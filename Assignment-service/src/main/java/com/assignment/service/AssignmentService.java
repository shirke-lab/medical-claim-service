package com.assignment.service;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

import com.assignment.model.ClaimAssignment;
import com.assignment.repository.ClaimAssignmentRepository;
import com.assignment.rabbit.AssignmentProducer;
import com.assignment.dto.AssignmentMessage;

@Service
public class AssignmentService {

    private final ClaimAssignmentRepository repo;
    private final AssignmentProducer producer;

    public AssignmentService(ClaimAssignmentRepository repo, AssignmentProducer producer) {
        this.repo = repo;
        this.producer = producer;
    }
    
    public List<ClaimAssignment> findPending() {
        return repo.findByStatus("SUBMITTED");
    }
    
    public ClaimAssignment assignClaim(Long claimId, String employeeId, String assignerId, String approverId) {
        ClaimAssignment a = new ClaimAssignment();
        a.setClaimId(claimId);
        a.setEmployeeId(employeeId);
        a.setAssignerId(assignerId);
        a.setApproverId(approverId);
        a.setStatus("ASSIGNED");
        a.setAssignedAt(new Date());
        ClaimAssignment saved = repo.save(a);

        AssignmentMessage msg = new AssignmentMessage();
        msg.setClaimId(claimId);
        msg.setEmployeeId(employeeId);
        msg.setApproverId(approverId);
        msg.setAssignerId(assignerId);

        producer.sendAssignment(msg);

        return saved;
    }
}
