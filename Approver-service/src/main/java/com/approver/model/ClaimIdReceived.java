package com.approver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class ClaimIdReceived {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private Long claimId;
		public Long getClaimId() {
			return claimId;
		}
				public void setClaimId(Long claimId) {
			this.claimId = claimId;
		}
				
}