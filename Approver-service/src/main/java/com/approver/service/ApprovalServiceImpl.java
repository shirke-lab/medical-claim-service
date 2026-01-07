package com.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Approval;
import com.model.ClaimApproval;
import com.repository.ApprovalRepository;


@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepo;

    @Override
    public Approval createApproval(Long claimId, String employeeId, String approverId) {
        Approval approval = new Approval();
        approval.setClaimId(claimId);
        approval.setEmployeeId(employeeId);
        approval.setApproverId(approverId);
        return approvalRepo.save(approval);
    }

    @Override
    public List<Approval> findPendingApprovals() { 
        return approvalRepo.findByStatus("PENDING");
    }

    @Override
    public Approval approve(Long approvalId, String remarks) {
        Approval approval = approvalRepo.findById(approvalId)
                .orElseThrow(() -> new RuntimeException("Approval Not Found"));

        approval.setStatus("APPROVED");
        approval.setRemarks(remarks);

        return approvalRepo.save(approval);
    }

    @Override
    public Approval reject(Long approvalId, String remarks) {
        Approval approval = approvalRepo.findById(approvalId)
                .orElseThrow(() -> new RuntimeException("Approval Not Found"));

        approval.setStatus("REJECTED");
        approval.setRemarks(remarks);

        return approvalRepo.save(approval);
    }
    public Approval decide(Long id, String status, String remarks) {
        Approval c = approvalRepo.findById(id).orElseThrow();
        c.setStatus(status); // APPROVED or REJECTED
        c.setRemarks(remarks);
        return approvalRepo.save(c);
    }

	public ClaimApproval assignApprover(Long id, String approverId) {
		// TODO Auto-generated method stub
		return null;
	}
}
