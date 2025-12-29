package com.claim.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.claim.model.ClaimRequest;
import com.claim.service.ClaimService;

@RestController
@RequestMapping("/claims")
public class claimController {
	private final ClaimService claimService;
	claimController(ClaimService claimService){
		this.claimService= claimService;
	}
		@PostMapping("/create")
	public ResponseEntity<?> createClaim(@RequestBody ClaimRequest request){
		System.out.println("employee id is "+request.getempid());
		System.out.println("claim description -"+request.getdescription());
		System.out.println("amount is -"+request.getamount());
		
				return ResponseEntity.ok(claimService.createClaim(request));
	}	}
