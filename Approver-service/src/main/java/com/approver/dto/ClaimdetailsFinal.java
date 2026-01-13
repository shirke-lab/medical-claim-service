package com.approver.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class ClaimdetailsFinal {
	@jakarta.persistence.Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long Id;
	private Long claimId;
    private String empId;
    private Double amount;
    private String description;

	
    public Long getClaimId() {
		return claimId;
	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String employeeId) {
		this.empId = employeeId;
	}
	public Double getAmount() {
		return amount;
	}
	public String getDescription() {
		return description;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
