package com.claim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.model.Claim;
import com.claim.model.ClaimRequest;
import com.claim.repository.ClaimRepository;

@Service
public class ClaimService {
	private final ClaimProducer producer;
	private final ClaimRepository repo;
	 ClaimService(ClaimRepository repo,ClaimProducer producer){
		 this.repo=repo;
		 this.producer= producer;
	 }
	      

	    public Claim createClaim(ClaimRequest req) {
		Claim claim= new Claim();
		claim.setEmpid(req.getEmpId());
		claim.setAmount(req.getAmount());
		claim.setDescription(req.getDescription());
		
		Claim saved=repo.save(claim);
		producer.sendClaim(saved);
		return saved;
	}
}
