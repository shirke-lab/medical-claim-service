package com.approver.model;

import jakarta.persistence.Entity;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class ClaimDecision {

	@Id
	private long id;
	private String claimId;
	 private String empId;
	    private String approverId;
	    private String status;   // PENDING / APPROVED / REJECTED
	    private String remarks;


	    private Instant createdAt = Instant.now();
	    private Instant updatedAt = Instant.now();

	  public ClaimDecision() {
		// TODO Auto-generated constructor stub
	}
		public String getempId() {
			return empId;
		}
		
		public void setClaimId(long id) {
			this.id=id;
		}

		public void setempId(String employeeId) {
			this.empId = employeeId;
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
		public String getclaimId() {
			return claimId;
		}
		public Instant getCreatedAt() {
			return createdAt;
		}
		public Instant getUpdatedAt() {
			return updatedAt;
		}
		public void setclaimId(String claim_id) {
			this.claimId = claim_id;
		}
		public void setCreatedAt(Instant createdAt) {
			this.createdAt = createdAt;
		}
		public void setUpdatedAt(Instant updatedAt) {
			this.updatedAt = updatedAt;
		}

	    // getters and setters
	}		

