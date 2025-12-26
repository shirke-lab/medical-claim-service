package com.auth.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.auth.model.User;
import com.auth.model.UserLoginRequest;
import com.auth.model.createUserRequest;
import com.auth.repository.UserRepository;
import com.auth.util.JwtResponse;
import com.auth.util.JwtUtili;

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
        }System.out.println("CREATE USER API HIT");

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
    }

    @GetMapping("/allUsers")
    public List<User> getAllUserList(){
    	
    	List<User> userList=userrepo.findAll();
    	System.out.println("all users list is providing");
    	//System.out.println(userList.toString());
    	userList.forEach(u->System.out.println(u));
    	
    	return userList;
    	
    }


}