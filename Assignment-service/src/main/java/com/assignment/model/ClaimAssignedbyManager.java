package com.assignment.model;

import java.util.Date;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ClaimAssignedbyManager")
public class ClaimAssignedbyManager {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="claimid", nullable=false, unique=true)
	private Long claimId; 
	@Nonnull
	private String ApproverId;
	@Nonnull
	private String ManagerId;
	 @Enumerated(EnumType.STRING) 
	private assignmentStatus status;
	private Date Assignedat;
	
	public assignmentStatus getStatus() {
		return status;
	}
	public void setStatus(assignmentStatus status) {
		this.status=status;
	}	

public Long getclaimId() {
	return claimId;
}
public void setclaimId(Long claimid) {
	this.claimId = claimid;
}
public String getApproverId() {
	return ApproverId;
}
public void setApproverId(String approverId) {
	this.ApproverId = approverId;
}
public String getManagerId() {
	return ManagerId;
}
public void setManagerId(String managerId) {
	this.ManagerId = managerId;
}
public Date getAssignedat() {
	return Assignedat;
}
public void setAssignedat(Date assignedat) {
	this.Assignedat = assignedat;
} 

public enum assignmentStatus {
		ASSIGNED, REASSIGNED
}	}

