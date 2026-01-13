package com.approver.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.approver.dto.ClaimdetailsFinal;

//import com.approver.model.Approval;


public interface ApprovalRepository extends JpaRepository<ClaimdetailsFinal, Long> {

}
