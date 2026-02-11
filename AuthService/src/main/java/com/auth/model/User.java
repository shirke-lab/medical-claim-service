package com.auth.model;


import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String userid;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        ADMIN, EMPLOYEE, APPROVER, MANAGER
    }
@Column(nullable=false)
    private int failedAttempts=0;
    private LocalDateTime lockTime;
    
    public int getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	public LocalDateTime getLockTime() {
		return lockTime;
	}

	public void setLockTime(LocalDateTime lockTime) {
		this.lockTime = lockTime;
	}

	// getters and setters
    public Long getId() { return id; }

    public String getUserid() { return userid; }
    public void setUserid(String userid) { this.userid = userid; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String toString() { 
    	return "User{id=" + id +
            ", userid='" + userid + '\'' +
            ", role=" + role +
            '}';  }

public boolean isAccountLocked() {
	if(lockTime==null) {return false;}
	return lockTime.plusMinutes(15).isAfter(lockTime.now());
}
public void resetLock() {
	this.failedAttempts=0;
	this.lockTime=null;
}
}