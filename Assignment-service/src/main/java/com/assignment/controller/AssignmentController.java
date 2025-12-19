package com.assignment.controller;

import org.springframework.web.bind.annotation.*;
import com.assignment.service.AssignmentService;
import com.assignment.model.ClaimAssignment;
import java.util.List;

@RestController
@RequestMapping("/assign")
public class AssignmentController {

    private final AssignmentService service;
    public AssignmentController(AssignmentService service) { this.service = service; }

    // list submitted claims (status SUBMITTED)
    @GetMapping("/pending")
    public List<ClaimAssignment> pending() {
        return service.findPending(); // implement findPending to fetch status=SUBMITTED
    }

    // assign to approver
    @PostMapping("/{claimId}")
    public ClaimAssignment assign(@PathVariable Long claimId,
                                  @RequestParam String employeeId,
                                  @RequestParam String assignerId,
                                  @RequestParam String approverId) {
        return service.assignClaim(claimId, employeeId, assignerId, approverId);
    }
}
