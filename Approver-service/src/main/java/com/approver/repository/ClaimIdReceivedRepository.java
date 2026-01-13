package com.approver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.approver.model.ClaimIdReceived;

@Repository
	public interface ClaimIdReceivedRepository extends JpaRepository<ClaimIdReceived, Long> {

	}

