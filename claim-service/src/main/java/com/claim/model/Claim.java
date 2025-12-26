package com.claim.model;

import java.util.Date;
import jakarta.persistence.*;
@Entity
public class Claim {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long claimId;
	private String empid;
	@Enumerated(EnumType.STRING)
	private claimStatus status= claimStatus.PENDING;
	private String description;
	private double amount;
	private Date claimCreatedOn= new Date();  
	
	public enum claimStatus{
		PENDING, APPROVED, REJECTED;
	}

	public void setClaimId(long claimId) {
		this.claimId = claimId;
	}

	public void setStatus(claimStatus status) {
		this.status = status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setClaimCreatedOn(Date claimCreatedOn) {
		this.claimCreatedOn = claimCreatedOn;
	}

	public long getClaimId() {
		return claimId;
	}

	public String getEmpid() {
		return empid;
	}

	public claimStatus getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	public double getAmount() {
		return amount;
	}

	public Date getClaimCreatedOn() {
		return claimCreatedOn;
	}

	public void setEmpid(String empId2) {
		// TODO Auto-generated method stub
	this.empid=	empId2;
	}
	
}
