package com.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

import com.model.User;
import com.model.createUserRequest;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUserid(String uname);
	public createUserRequest save(createUserRequest user);// added for troubleshooting 
}
