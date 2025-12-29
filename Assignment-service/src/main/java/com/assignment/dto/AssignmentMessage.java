package com.assignment.dto;

import java.io.Serializable;

public class AssignmentMessage implements Serializable {
	   public AssignmentMessage()
	   {
	   }
	   
    private Long claimId;
    private String employeeId;
//    private String approverId;
//    private String assignerId;
    private String status;
    
    public void setStatus(String status) {
    	this.status= status;    }   
    	public Long getClaimId() {
		return claimId;	}
	public String getEmployeeId() {
		return employeeId;	}
//	public String getApproverId() {
//		return approverId;	}
//	public String getAssignerId() {
//		return assignerId;	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;	}
//	public void setApproverId(String approverId) {
//		this.approverId = approverId;	}
//	public void setAssignerId(String assignerId) {
//		this.assignerId = assignerId;	}

}
