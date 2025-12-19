package com.service;



import java.util.List;

import com.model.Approval;


public interface ApprovalService {

    Approval createApproval(Long claimId, String employeeId, String approverId);

    List<Approval> findPendingApprovals();

    Approval approve(Long approvalId, String remarks);

    Approval reject(Long approvalId, String remarks);
}
