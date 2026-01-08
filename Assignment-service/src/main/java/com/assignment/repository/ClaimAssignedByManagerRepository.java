package com.assignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.ClaimAssignedbyManager;

@Repository
public interface ClaimAssignedByManagerRepository extends JpaRepository<ClaimAssignedbyManager, Long> {

	 //public ClaimAssignedbyManager  save(ClaimAssignedbyManager ca);
	
	 public Optional<ClaimAssignedbyManager> findByclaimId(Long claimID);
}
