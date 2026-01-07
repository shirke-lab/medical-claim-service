package com.model;

import jakarta.persistence.Entity;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class ClaimApproval {

	@Id
	private long id;
	private String claim_id;
	 private String employeeId;
	    private String approverId;
	    private String status;   // PENDING / APPROVED / REJECTED
	    private String remarks;


	    private Instant createdAt = Instant.now();
	    private Instant updatedAt = Instant.now();

	   public ClaimApproval() {}
		public String getEmployeeId() {
			return employeeId;
		}
		
		public void setClaimId(long id) {
			this.id=id;
		}

		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}

		public String getApproverId() {
			return approverId;
		}

		public void setApproverId(String approverId) {
			this.approverId = approverId;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getClaim_id() {
			return claim_id;
		}
		public Instant getCreatedAt() {
			return createdAt;
		}
		public Instant getUpdatedAt() {
			return updatedAt;
		}
		public void setClaim_id(String claim_id) {
			this.claim_id = claim_id;
		}
		public void setCreatedAt(Instant createdAt) {
			this.createdAt = createdAt;
		}
		public void setUpdatedAt(Instant updatedAt) {
			this.updatedAt = updatedAt;
		}

	    // getters and setters
	}		

