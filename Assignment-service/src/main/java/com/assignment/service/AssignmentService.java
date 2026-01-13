package com.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.assignment.model.ClaimAssignedbyManager;
import com.assignment.model.ClaimAssignedbyManager.assignmentStatus;
import com.assignment.model.ClaimAssignment;
import com.assignment.repository.ClaimAssignedByManagerRepository;
import com.assignment.repository.ClaimAssignmentRepository;
import com.assignment.util.Jwtutil;

import jakarta.persistence.EnumeratedValue;
import jakarta.transaction.Transactional;

import com.assignment.dto.AssignmentMessage;

@Service
public class AssignmentService {
@Autowired
	public Jwtutil jwtutil;
	
    private final ClaimAssignmentRepository repo;
    private final AssignmentProducer producer;
private final ClaimAssignedByManagerRepository cabmrepo;
    public AssignmentService(ClaimAssignmentRepository repo, AssignmentProducer producer, ClaimAssignedByManagerRepository cabmrepo) {
        this.repo = repo;
        this.producer = producer;
        this.cabmrepo=cabmrepo;
    }
    
    public List<ClaimAssignment> findPending() {
        return repo.findByStatus("pending");
    }
    @Transactional
    public String assignClaimsToApprovers(Long claimId, String approverId) {
    	
    	Optional<ClaimAssignedbyManager> claimopt=cabmrepo.findByclaimId(claimId);
 		if(claimopt.isPresent()) {
 			ClaimAssignedbyManager cabm=claimopt.get();
 			String claimApproverId=cabm.getApproverId();
 			
 			
 			  if (claimApproverId.equals(approverId)) {
 		            System.out.println("Claim " + claimId + " is already assigned to approver - " + claimApproverId);
 			  
 		            return "Claim is already assigned to approver " + claimApproverId;
 		        }
 			  else {
 				  cabm.setApproverId(approverId);
 				  cabm.setAssignedat(new Date());
 				  cabm.setManagerId(jwtutil.getCurrentUSerId());
 				 // @EnumeratedValue(value=assignmentStatus)
 				  cabm.setStatus(assignmentStatus.REASSIGNED);
 				 producer.sendAssignedbyManager(cabm);
 				  return "claim is reassigned to approver "+approverId;
 			  }
 		    }

 		    // If claim not found or approver not yet assigned
 		    String managerId = jwtutil.getCurrentUSerId();
 		    System.out.println("ManagerId " + managerId);
 		    System.out.println("Claim id is at service " + claimId);
 		    System.out.println("Approver id is at service " + approverId);

 		    ClaimAssignedbyManager cabm = new ClaimAssignedbyManager();
 		    cabm.setApproverId(approverId);
 		    cabm.setclaimId(claimId);
 		    cabm.setManagerId(managerId);
 		    cabm.setStatus(assignmentStatus.ASSIGNED);
 		    cabm.setAssignedat(new Date());
 		   
 		    cabmrepo.save(cabm);
 		    repo.updateStatus(claimId, "ASSIGNED");//update status in claim assignment table
 		   
 				 producer.sendAssignedbyManager(cabm);
 				   
 		    return "Claim " + claimId + " is assigned to " + approverId + " by " + managerId;
 		}
}