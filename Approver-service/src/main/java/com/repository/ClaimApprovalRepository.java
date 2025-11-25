package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.ClaimApproval;

@Repository
	public interface ClaimApprovalRepository extends JpaRepository<ClaimApproval, Long> {

	    List<ClaimApproval> findByApproverIdAndStatus(String approverId, String status);

	}

