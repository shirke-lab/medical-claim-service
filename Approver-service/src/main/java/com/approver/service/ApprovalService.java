package com.approver.service;
import com.approver.dto.ClaimdetailsFinal;
import com.approver.repository.ApprovalRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApprovalService {

    private final RestTemplate restTemplate;
    private final ApprovalRepository aprepo;
    

        public ApprovalService(RestTemplate restTemplate, ApprovalRepository aprepo) {
        this.restTemplate = restTemplate;
        this.aprepo=aprepo;
    }
//below is the method doing api call for retriving data from claim service we need to provide token for authentication is claim service.
    
    public ClaimdetailsFinal getClaimDetailsfromClaimService(Long claimId) {
        // If your claim service requires authentication, set headers here
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpYXQiOjE3NjgzMTE4NjUsImV4cCI6MTc2ODMxNzg2NX0.VyuuMxXCQHveLXCWbbjg1X33BfgLamLgJUHOZngF380");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ClaimdetailsFinal> response = restTemplate.exchange(
                "http://localhost:8081/claims/getClaim/" + claimId,
                HttpMethod.GET,
                entity,
                ClaimdetailsFinal.class
        );

        ClaimdetailsFinal dto = response.getBody();

        if (dto != null) {
            System.out.println("ClaimId: " + dto.getClaimId());
            System.out.println("Amount: " + dto.getAmount());
            System.out.println("EmpId: " + dto.getEmpId());
            System.out.println("description: " + dto.getDescription());
            aprepo.save(dto);          
 
        } else {
            System.out.println("No claim details found for ID: " + claimId);
        }

        return dto;
    }
}

/*
import java.net.http.HttpHeaders;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import org.springframework.http.*;

import com.approver.dto.ClaimMessage;
import com.approver.dto.ClaimdetailsFinal;
import com.approver.model.*;
import com.approver.repository.*;


@Service
public class ApprovalService {
@Autowired
    RestTemplate rest; 
    
HttpHeaders headers = new HttpHeaders();
//@Override
//    public Approval createApproval(Long claimId, String employeeId, String approverId) {
//        Approval approval = new Approval();
//        approval.setClaimId(claimId);
//        approval.setEmployeeId(employeeId);
//        approval.setApproverId(approverId);
//        return approvalRepo.save(approval);
//    }
//
//    @Override
//    public List<Approval> findPendingApprovals() { 
//        return approvalRepo.findByStatus("PENDING");
//    }
//
//    @Override
//    public Approval approve(Long approvalId, String remarks) {
//        Approval approval = approvalRepo.findById(approvalId)
//                .orElseThrow(() -> new RuntimeException("Approval Not Found"));
// 
//        approval.setStatus("APPROVED");
//        approval.setRemarks(remarks);
//
//        return approvalRepo.save(approval);
//    }
//
//    @Override
//    public Approval reject(Long approvalId, String remarks) {
//        Approval approval = approvalRepo.findById(approvalId)
//                .orElseThrow(() -> new RuntimeException("Approval Not Found"));
//
//        approval.setStatus("REJECTED");
//        approval.setRemarks(remarks);
//
//        return approvalRepo.save(approval);
//    }
//    public Approval decide(Long id, String status, String remarks) {
//        Approval c = approvalRepo.findById(id).orElseThrow();
//        c.setStatus(status); // APPROVED or REJECTED
//        c.setRemarks(remarks);
//        return approvalRepo.save(c);
//    }



	public ClaimdetailsFinal getClaimDetailsfromClaimService(Long claimId) {
	ClaimdetailsFinal dto=rest.getForObject("http://localhost:8081/claims/"+claimId, ClaimdetailsFinal.class);
	HttpHeaders headers = new HttpHeaders(null);
	headers.set("Authorization", "Bearer " + jwtToken);

	HttpEntity<String> entity = new HttpEntity<>(headers);

	ResponseEntity<ClaimdetailsFinal> response = rest.exchange(
	    "http://localhost:8081/claims/" + claimId,
	    HttpMethod.GET,
	    entity,
	    ClaimdetailsFinal.class
	);

		ClaimdetailsFinal finaldetails=new ClaimdetailsFinal();
    	finaldetails.setClaimId(dto.getClaimId());
    	finaldetails.setEmployeeId(dto.getEmployeeId());
    	finaldetails.setAmount(dto.getAmount());
    	finaldetails.setDescription(dto.getDescription());
    	System.out.println(dto.getClaimId());
    	System.out.println(dto.getAmount());
return finaldetails;
	}
}
*/