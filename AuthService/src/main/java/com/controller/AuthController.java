package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.model.User;
import com.model.UserLoginRequest;
import com.repository.UserRepository;
//import com.model.UserLoginRequest;
import com.util.JwtResponse;
import com.util.JwtUtili;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userrepo;

    @Autowired
    private JwtUtili jwtutil;

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

        String rolePrefix = user.getRole().startsWith("ROLE_") ? user.getRole() : "ROLE_" + user.getRole();

        String token = jwtutil.generateToken(user.getUserid(), List.of(rolePrefix));

        return ResponseEntity.ok(new JwtResponse(token));
    }}