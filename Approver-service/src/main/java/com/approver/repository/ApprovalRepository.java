package com.approver.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.approver.dto.ClaimdetailsFinal;

public interface ApprovalRepository extends JpaRepository<ClaimdetailsFinal, Long> {

	Optional<ClaimdetailsFinal> findAllByclaimId(Long claimId);

}
