package com.assignment.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class ClaimAssignment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private Long claimId;
	private String employeeId;
	private String assignerId;
	private String approverId;
	private String status;
	private Date assignedAt;
	public Long getClaimId() {
		return claimId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public String getAssignerId() {
		return assignerId;
	}
	public String getApproverId() {
		return approverId;
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
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public void setAssignerId(String assignerId) {
		this.assignerId = assignerId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setAssignedAt(Date assignedAt) {
		this.assignedAt = assignedAt;
	}
	
	
	


}
