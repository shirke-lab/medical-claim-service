package com.claim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
//import com.claim.dto.ClaimMessage;
import com.claim.dto.Claimdetails;
import com.claim.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

	@SuppressWarnings("unchecked")
	public Claim save(Claim claim);
Optional<Claim> findByclaimId(Long claimId); 
	
	
}
