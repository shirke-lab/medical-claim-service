package com.approver.controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.approver.dto.ClaimdetailsFinal;
import com.approver.model.ClaimIdReceived;
import com.approver.repository.*;
import com.approver.service.ApprovalService;
@RestController
@RequestMapping("/approval")
public class ApprovalController {

	private final ApprovalRepository aprepo;
    private final ClaimIdReceivedRepository cdrepo;
    private final ApprovalService service;

    public ApprovalController(ClaimIdReceivedRepository cdrepo, ApprovalRepository aprepo, ApprovalService service) {
        this.cdrepo = cdrepo;
        this.aprepo=aprepo;
        this.service=service;
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
    @PatchMapping("/decision/{claimId}")
    public ResponseEntity<ClaimdetailsFinal> decision(
            @PathVariable Long claimId,
            @RequestParam String status,
            @RequestParam String remarks) {
       
    	ClaimdetailsFinal cdf=(aprepo.findAllByclaimId(claimId)).orElseThrow(()->new RuntimeException("claim id not found "));
    	
    	 cdf.setstatus(status);   // e.g. "Approved" or "Rejected"
    	    cdf.setRemarks(remarks); // approver's remarks

    	    // Save updated entity
    	    ClaimdetailsFinal updated = aprepo.save(cdf);


    	
    	
        return ResponseEntity.ok(updated);
    }}