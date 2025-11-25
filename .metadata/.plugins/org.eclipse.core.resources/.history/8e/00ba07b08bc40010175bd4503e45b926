package com.example.db1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

import com.example.model.db1.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<?> findBymobileNo(String no);
	public Optional<User> findByUserid(String uid);
	
}
