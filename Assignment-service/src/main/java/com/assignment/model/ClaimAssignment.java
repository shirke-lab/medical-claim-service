package com.assignment.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
public class ClaimAssignment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Nonnull
	private Long claimId;
private double amount;
    @JsonProperty("empid")
	private String empid;
//	private String assignerId;
//	private String approverId;
	private String status;
	private Date assignedAt;
	public Long getClaimId() {
		return claimId;
	}
	public String getEmpId() {
		return empid;
	}
public void setAmount(double amount) {
	this.amount=amount;
}
public double getAmount() {
	return amount;
}
	
	public String getStatus() {
		return status;
	}
	public Date getAssignedAt() {
		return assignedAt;
	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}
	public void setEmpId(String empid) {
		this.empid = empid;
	}
//	public void setAssignerId(String assignerId) {
//		this.assignerId = assignerId;
//	}
//	public void setApproverId(String approverId) {
//		this.approverId = approverId;
//	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setAssignedAt(Date assignedAt) {
		this.assignedAt = assignedAt;
	}
	}
