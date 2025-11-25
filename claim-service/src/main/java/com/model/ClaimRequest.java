package com.model;


public class ClaimRequest {

	public double Amount;
	public String EmpId;
	public String Description;
	
	
	
	public String getEmpId() {
		return EmpId;
	}
	
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount) {
		Amount = amount;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public void setEmpId(String empId) {
		EmpId = empId;
	}
	
	
	
}
