package com.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.ClaimAssignment;

@Repository
public interface ClaimAssignmentRepository extends JpaRepository<ClaimAssignment,  Long > {

	List<ClaimAssignment> findByStatus(String status);
	
    List<ClaimAssignment> findByApproverIdAndStatus(String approverId, String status);
}
