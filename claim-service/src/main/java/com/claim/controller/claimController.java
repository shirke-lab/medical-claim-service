package com.claim.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.claim.dto.Claimdetails;
import com.claim.model.ClaimRequest;
import com.claim.repository.ClaimRepository;
import com.claim.service.ClaimService;

@RestController
@RequestMapping("/claims")
public class claimController {
	private final ClaimService claimService;
	private final ClaimRepository claimrepo;
	
	claimController(ClaimService claimService,ClaimRepository claimrepo){
				this.claimService= claimService;
				this.claimrepo=claimrepo;
	}
		@PostMapping("/create")
	public ResponseEntity<?> createClaim(@RequestBody ClaimRequest request){
		System.out.println("employee id is "+request.getempid());
		System.out.println("claim description -"+request.getdescription());
		System.out.println("amount is -"+request.getamount());
		
				return ResponseEntity.ok(claimService.createClaim(request));
	}	
		
@GetMapping("/getClaim/{claimId}") 
public ResponseEntity<Claimdetails> getClaimDetails(@PathVariable Long claimId){

	return claimrepo.findByclaimId(claimId)
			.map(claim-> {
				Claimdetails cmsg=new Claimdetails();
		cmsg.setClaimId(claim.getClaimId());
		cmsg.setEmpId(claim.getEmpid());
		cmsg.setAmount(claim.getAmount());
		cmsg.setDescription(claim.getDescription());
		System.out.println(claim.getEmpid());
		System.out.println(cmsg.getEmpId());
		return cmsg;
			})
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	
}
}