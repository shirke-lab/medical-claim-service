package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.ClaimApproval;
import com.repository.ClaimApprovalRepository;

@RestController
@RequestMapping("/approval")
public class ApprovalController {
	private final ClaimApprovalRepository repo;

    public ApprovalController(ClaimApprovalRepository repo) {
        this.repo = repo;
    }

    // Fetch all pending claims for this approver
    @GetMapping("/pending/{approverId}")
    public List<ClaimApproval> getPending(@PathVariable String approverId) {
        return repo.findByApproverIdAndStatus(approverId, "PENDING");
    }

    // Approve or Reject
    @PostMapping("/decision/{id}")
    public String updateDecision(
            @PathVariable Long id,
            @RequestParam String status, 
            @RequestParam(required = false) String remarks) {

        ClaimApproval ca = repo.findById(id).orElseThrow();

        ca.setStatus(status); // APPROVED or REJECTED
        ca.setRemarks(remarks);

        repo.save(ca);

        return "Decision saved.";
    }
}
	

