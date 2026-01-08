package com.assignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.dto.AssignmentMessage;
import com.assignment.model.ClaimAssignedbyManager;
import com.assignment.model.ClaimAssignment;

@Repository
public interface ClaimAssignmentRepository extends JpaRepository<ClaimAssignment,  Long > {

	List<ClaimAssignment> findByStatus(String status);

	Optional<ClaimAssignment> findByclaimId(Long claimId);
 void deleteByclaimId(Long claimId) ;

 @Modifying
 @Query("""
     update ClaimAssignment c
     set c.status = :status,
         c.assignedAt = CURRENT_TIMESTAMP
     where c.claimId = :claimId
 """)
 int updateStatus(@Param("claimId")Long claimID,
		 			@Param("status") String status);
	
	
}
