package com.approver.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.approver.model.Approval;
import com.approver.model.*;
import com.approver.service.ApprovalService;
import com.approver.service.ApprovalServiceImpl;

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
    public ResponseEntity<ClaimDecision> assign(@PathVariable Long id, @RequestParam String approverId) {
    	ClaimDecision updated = service.assignApprover(id, approverId);
        return ResponseEntity.ok(updated);
    }
}
