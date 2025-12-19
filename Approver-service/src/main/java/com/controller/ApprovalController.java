package com.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.Approval;
import com.model.ClaimApproval;
import com.service.ApprovalService;
import com.service.ApprovalServiceImpl;

@RestController
@RequestMapping("/approval")
public class ApprovalController {

    private final ApprovalServiceImpl service;

    public ApprovalController(ApprovalServiceImpl service) {
        this.service = service;
    }

    // GET all pending (SUBMITTED)
    @GetMapping("/pending")
    public ResponseEntity<List<Approval>> getPending() {
        return ResponseEntity.ok(service.findPendingApprovals());
    }

    // Approve or reject
    @PostMapping("/decision/{id}")
    public ResponseEntity<Approval> decision(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required=false) String remarks) {
        Approval updated = service.decide(id, status, remarks);
        return ResponseEntity.ok(updated);
    }

    // Assign an approver (optional)
    @PostMapping("/assign/{id}")
    public ResponseEntity<ClaimApproval> assign(@PathVariable Long id, @RequestParam String approverId) {
        ClaimApproval updated = service.assignApprover(id, approverId);
        return ResponseEntity.ok(updated);
    }
}
