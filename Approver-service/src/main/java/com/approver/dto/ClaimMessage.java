package com.rabbit;

public class ClaimMessage {
    private Long claimId;
    private String employeeId;
    private Double amount;
    private String description;

	
    public Long getClaimId() {
		return claimId;
	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

    
    
    // getters, setters
}