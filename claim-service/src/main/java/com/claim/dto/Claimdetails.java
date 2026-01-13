package com.claim.dto;

public class Claimdetails {

    private Long claimId;
    private String empId;
    private double amount;
    private String description;
	public Long getClaimId() {
		return claimId;	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;	}
	public String getEmpId() {
		return empId;	}
	public void setEmpId(String empId) {
		this.empId = empId;	}
	public double getAmount() {
		return amount;	}
	public void setAmount(double amount) {
		this.amount = amount;	}
	public String getDescription() {
		return description;	}
	public void setDescription(String description) {
		this.description = description;
	}   }
