package com.approver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.approver.model.ClaimDecision;

@Repository
	public interface ClaimDecisionRepository extends JpaRepository<ClaimDecision, Long> {

	    List<ClaimDecision> findByApproverIdAndStatus(String approverId, String status);

	}

