package com.claim.model;


public class ClaimRequest {

	public double amount;
	public String empid;
	public String description;
	
	public String getempid() {
		return empid;
	}
	
	public double getamount() {
		return amount;
	}
	public void setamount(double amount) {
		amount = amount;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		description = description;
	}
	public void setempid(String empid) {
		empid = empid;
	}
	public String toString() {
		return "employee id is -"+empid+"Description is - "+description+"Amount is - "+amount;
	}
	
	
}
