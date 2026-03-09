package com.auth.model;


import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import com.auth.repository.UserRepository;

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
private int failedAttempt;
private LocalDateTime lockTime;
private boolean accountNonLocked = true;
private LocalDateTime lastFailedAttemptTime;
private static final int MAX_FAILED_ATTEMPTS = 3;
private static final long LOCK_TIME_DURATION = 15; // minutes

    public int getFailedAttempts() {
		return failedAttempt;
	}

	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempt = failedAttempts;
	}

	public LocalDateTime getLastFailedAttemptTime() {
		return lastFailedAttemptTime;
	}

	public void setLastFailedAttemptTime(LocalDateTime lastFailedAttemptTime) {
		this.lastFailedAttemptTime = lastFailedAttemptTime;
	}

	public LocalDateTime getLockTime() {
		return lockTime;
	}

	public void setLockTime(LocalDateTime lockTime) {
		this.lockTime = lockTime;
	}

public boolean isAccountNonLocked() {
	return accountNonLocked;
}

public void setAccountNonLocked(boolean accountNonLocked) {
	this.accountNonLocked = accountNonLocked;
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
}