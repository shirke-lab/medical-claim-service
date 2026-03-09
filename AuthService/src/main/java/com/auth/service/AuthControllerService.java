package com.auth.service;
import java.time.Duration;
import java.time.LocalDateTime;


import org.springframework.stereotype.Service;

import com.auth.model.*;
import com.auth.repository.*;


@Service
public class AuthControllerService {
LocalDateTime lockTime;
	
	private final UserRepository urepo ;
	   public AuthControllerService(UserRepository urepo){
		   this.urepo=urepo;
		   	   } 	

	    private static final int MAX_FAILED_ATTEMPTS = 3;
	    private static final long LOCK_TIME_DURATION = 15; // minutes
	
    public void lock(User user) {
    	user.setAccountNonLocked(false);
    	user.setLockTime(lockTime.now());
    }
    public void increaseFailedAttempts(User user) {
        int attempts = user.getFailedAttempts() + 1;
        user.setFailedAttempts(attempts);
        user.setLastFailedAttemptTime(LocalDateTime.now());

        if (attempts >= MAX_FAILED_ATTEMPTS) {
            user.setAccountNonLocked(false);
            user.setLockTime(LocalDateTime.now());
        }

        urepo.save(user);
    }

    public void resetFailedAttempts(User user) {
        user.setFailedAttempts(0);
        user.setLastFailedAttemptTime(null);
        urepo.save(user);
    }

    public void unlockIfLockExpired(User user) {

        // If lockTime is null → user was never locked
        if (user.getLockTime() == null) {
            return;
        }

        // Check if 15 minutes passed
        if (user.getLockTime().plusMinutes(15).isBefore(LocalDateTime.now())) {

            user.setFailedAttempts(0);
            user.setLockTime(null);
            user.setAccountNonLocked(true);
            urepo.save(user);
        }
    }
    
    public void resetCounterIfTimeExpired(User user) {

        // Safety check: if user itself is null
        if (user == null) {
            return;
        }

        // If no failed attempts → nothing to reset
        if (user.getFailedAttempts() <= 0) {
            return;
        }

        // If account is locked → don't reset here (unlock method handles that)
        if (!user.isAccountNonLocked()) {
            return;
        }

        // If lastFailedAttemptTime is null → cannot calculate expiry
        if (user.getLastFailedAttemptTime() == null) {
            return;
        }

        LocalDateTime expiryTime =user.getLastFailedAttemptTime().plusMinutes(LOCK_TIME_DURATION);

       if (expiryTime.isBefore(LocalDateTime.now()))
        {
            user.setFailedAttempts(0);
            user.setLastFailedAttemptTime(null); // optional but recommended
            urepo.save(user);
            
        }
    }
    public long checkRemainingTime(User user) {
    LocalDateTime expiryTime=user.getLastFailedAttemptTime().plusMinutes(LOCK_TIME_DURATION);
    	Duration remainingDuration=Duration.between(expiryTime,lockTime.now());
    	Long remainigTime=remainingDuration.toMinutes();
    	return remainigTime;
    }
}