package com.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Approval;


public interface ApprovalRepository extends JpaRepository<Approval, Long> {

    List<Approval> findByStatus(String status);
    List<Approval> findByApproverId(String approverId);
    List<Approval> findByClaimId(Long claimId);
}
