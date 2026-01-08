package com.assignment.dto;

//import java.io.Serializable;

public class AssignmentMessage  {
	   public AssignmentMessage()
	   {
	   }
	   
    private Long claimId;
    private String empId;
    private double amount;
//    private String approverId;
//    private String assignerId;
    private String status;
    
    public void setStatus(String status) {
    	this.status= status;    }   
    	public Long getClaimId() {
		return claimId;	}
	public String getemployeeId() {
		return empId;	}
//	public String getApproverId() {
//		return approverId;	}
	public void setAmount(double amount) {
		this.amount=amount;
	}
	public double getAmount() {
		return amount;
	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;	}
	public void setempId(String employeeId) {
		this.empId = employeeId;	}
//	public void setApproverId(String approverId) {
//		this.approverId = approverId;	}
//	public void setAssignerId(String assignerId) {
//		this.assignerId = assignerId;	}

}
