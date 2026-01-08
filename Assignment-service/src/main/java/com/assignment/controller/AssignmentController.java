package com.assignment.controller;

import org.springframework.web.bind.annotation.*;
import com.assignment.service.AssignmentService;
import com.assignment.model.ClaimAssignedbyManager;
import com.assignment.model.ClaimAssignment;
import com.assignment.repository.ClaimAssignmentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assign")
public class AssignmentController {

    private final AssignmentService service;
    private final ClaimAssignmentRepository repo;
    public AssignmentController(AssignmentService service,ClaimAssignmentRepository repo ) { 
    	this.service = service; 
    	this.repo=repo;
    }

    // list submitted claims (status SUBMITTED)
    @GetMapping("/pending")
    public List<ClaimAssignment> pending() {
    	
    	
        return repo.findByStatus("pending"); 
    }

    // assign to approver
       
@PostMapping("/assignTo/{claimId}")
public String assignClaimsToApprover(@PathVariable Long claimId, @RequestParam String approverId) {

	Optional<String>claimIsThere=repo.findByclaimId(claimId).map(ClaimAssignment::getStatus);
//	System.out.println("controller here");
//	System.out.println("controller here claimId = "  +claimId);
//	System.out.println("controller here approverId = "+approverId);
	
	if(claimIsThere.isPresent()) {
	System.out.println("claim is present and will map to approvers");	
	
	return service.assignClaimsToApprovers(claimId, approverId);
	}
	else {
		return "claimId is not available";}}}