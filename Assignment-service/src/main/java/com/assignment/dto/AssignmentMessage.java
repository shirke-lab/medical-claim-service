package com.assignment.dto;

public class AssignmentMessage {
    private Long claimId;
    private String employeeId;
    private String approverId;
    private String assignerId;
    
	public Long getClaimId() {
		return claimId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public String getApproverId() {
		return approverId;
	}
	public String getAssignerId() {
		return assignerId;
	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public void setAssignerId(String assignerId) {
		this.assignerId = assignerId;
	}

    // getters/setters
}
