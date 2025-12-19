package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.model.User;
import com.model.UserLoginRequest;
import com.model.createUserRequest;
import com.repository.UserRepository;
//import com.model.UserLoginRequest;
import com.util.JwtResponse;
import com.util.JwtUtili;

@RestController
public class AuthController {

    
    private final UserRepository userrepo;
    private final JwtUtili jwtutil;
    
    public  AuthController(JwtUtili jwtutil, UserRepository userrepo ) {
    	this.userrepo = userrepo;
		this.jwtutil=jwtutil;
    }
    
    
private UserLoginRequest ulr;
    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) {

        System.out.println("User ID = " + loginRequest.getUserid());

        Optional<User> userOpt = userrepo.findByUserid(loginRequest.getUserid());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

       // String rolePrefix = user.getRole().startsWith("ROLE_") ? user.getRole() : "ROLE_" + user.getRole();
        
        String role = user.getRole().name();  // e.g. "ADMIN"
        //String role = roleName.startsWith("ROLE_") ? roleName : "ROLE_" + roleName;

        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
//        String token = jwtutil.generateToken(user.getUserid(), List.of(rolePrefix));

        String token = jwtutil.generateToken(user.getUserid(), List.of(role));

        return ResponseEntity.ok(new JwtResponse(token));
    }
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody createUserRequest req) {
        Optional<User> userAlreadyAvailable = userrepo.findByUserid(req.getUserid());
        if (userAlreadyAvailable.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("userid already present");
        }

        User user = new User();
        user.setUserid(req.getUserid());

        // encode password before saving
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        // convert role string to enum safely
        try {
            User.Role roleEnum = User.Role.valueOf(req.getRole().toUpperCase());
            user.setRole(roleEnum);
        } catch (IllegalArgumentException | NullPointerException ex) {
            return ResponseEntity.badRequest().body("Invalid role. Allowed: ADMIN, EMPLOYEE, APPROVER, ASSIGNER");
        }

        userrepo.save(user);
        return ResponseEntity.ok("User created successfully");
    }}