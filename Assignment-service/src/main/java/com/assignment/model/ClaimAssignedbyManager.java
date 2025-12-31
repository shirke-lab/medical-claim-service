package com.assignment.model;

import java.util.Date;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClaimAssignedbyManager {
	@Id
	private Long Claimid; 
	@Nonnull
	private String employeeId;
	@Nonnull
	private String ApproverId;
	@Nonnull
	private String ManagerId;
private Date Assignedat;
public Long getClaimid() {
	return Claimid;
}
public void setClaimid(Long claimid) {
	Claimid = claimid;
}
public String getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
}
public String getApproverId() {
	return ApproverId;
}
public void setApproverId(String approverId) {
	ApproverId = approverId;
}
public String getManagerId() {
	return ManagerId;
}
public void setManagerId(String managerId) {
	ManagerId = managerId;
}
public Date getAssignedat() {
	return Assignedat;
}
public void setAssignedat(Date assignedat) {
	Assignedat = assignedat;
} 
	


}
