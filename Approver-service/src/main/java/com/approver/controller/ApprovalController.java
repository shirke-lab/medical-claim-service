package com.approver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.*;

import com.approver.dto.ClaimdetailsFinal;
import com.approver.model.ClaimIdReceived;
import com.approver.repository.*;
import com.approver.service.ApprovalService;
//
//import com.approver.model.Approval;
//import com.approver.model.*;
//import com.approver.service.ApprovalService;
//import com.approver.service.ApprovalServiceImpl;

@RestController
@RequestMapping("/approval")
public class ApprovalController {

    private final ClaimIdReceivedRepository cdrepo;
@Autowired
ApprovalService service;
    public ApprovalController(ClaimIdReceivedRepository cdrepo) {
        this.cdrepo = cdrepo;
    }

    // GET all pending (Assigned)
  @GetMapping("/pending")
    public  List<ClaimIdReceived> getAll() {
	  List<ClaimIdReceived> pendingClaims=cdrepo.findAll();
	pendingClaims.forEach(System.out::println);
        return pendingClaims;
    }
//below method is for retriving data from claim service for complete claim details.
  //after receiving this data it is saved in db then approver will check and take decision. 
  @PostMapping("/claimDetails/{claimId}")
  public ResponseEntity<ClaimdetailsFinal> claimDetails(@PathVariable Long claimId){
		  ClaimdetailsFinal finadet=service.getClaimDetailsfromClaimService(claimId);
	return ResponseEntity.ok(finadet);
	  
  }
  
    // Approve or reject
//    @PostMapping("/decision/{id}")
//    public ResponseEntity<Approval> decision(
//            @PathVariable Long id,
//            @RequestParam String status,
//            @RequestParam(required=false) String remarks) {
//        Approval updated = service.decide(id, status, remarks);
//        return ResponseEntity.ok(updated);
//    }
//
//    // Assign an approver (optional)
//    @PostMapping("/assign/{id}")
//    public ResponseEntity<ClaimIdReceived> assign(@PathVariable Long id, @RequestParam String approverId) {
//    	ClaimIdReceived updated = service.assignApprover(id, approverId);
//        return ResponseEntity.ok(updated);
//    }
}
