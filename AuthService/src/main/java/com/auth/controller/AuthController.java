package com.auth.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.auth.AuthService;
import com.auth.model.User;
import com.auth.model.UserLoginRequest;
import com.auth.model.createUserRequest;
import com.auth.repository.UserRepository;
import com.auth.service.AuthControllerService;
import com.auth.util.JwtResponse;
import com.auth.util.JwtUtili;

import jakarta.transaction.Transactional;
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final UserRepository userrepo;
    private final JwtUtili jwtutil;
    
    public  AuthController(JwtUtili jwtutil, UserRepository userrepo ) {
    	this.userrepo = userrepo;
		this.jwtutil=jwtutil;
    }
    @Autowired
    AuthControllerService authServic;
    
    private UserLoginRequest ulr;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) {

        System.out.println("User ID = " + loginRequest.getUserid());
String s=loginRequest.getUserid();
        Optional<User> userOpt = userrepo.findByUserid(s);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User not found");
        }

        User user = userOpt.get();

        //Unlock account if lock duration expired
        authServic.unlockIfLockExpired(user);

        // Reset failed attempts if 15 mins passed after single attempt
        authServic.resetCounterIfTimeExpired(user);

        // Check if still locked
        if (!user.isAccountNonLocked()) {
        	Long RemainingMinutes=authServic.checkRemainingTime(user);        	
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Account is locked. You can try after "+RemainingMinutes+" minutes");
        }

        //  Validate password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

            authServic.increaseFailedAttempts(user);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials. Attempt "
                            + user.getFailedAttempts() + " of 3");
        }

        // 5️⃣ Successful login → reset counter
        authServic.resetFailedAttempts(user);

        String role = "ROLE_" + user.getRole().name();

        String token = jwtutil.generateToken(user.getUserid(), List.of(role));

        return ResponseEntity.ok(new JwtResponse(token));
    }
    
    @PostMapping("/createUser")
    @Transactional
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
            return ResponseEntity.badRequest().body("Invalid role. Allowed: ADMIN, EMPLOYEE, APPROVER, MANAGER");
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
        }}