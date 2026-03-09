package com.auth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

import com.auth.model.User;
import com.auth.model.createUserRequest;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUserid(String userid);
	       //Optional<User> findByUserid(String userid);
	//public createUserRequest save(createUserRequest user);// added for troubleshooting 
}
