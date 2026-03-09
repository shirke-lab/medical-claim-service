package com.claim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.model.*;

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
		claim.setEmpid(req.getempid());
		claim.setAmount(req.getamount());
		claim.setDescription(req.getdescription());
		
		Claim saved=repo.save(claim);
		producer.sendClaim(saved);
		return saved;
	}
	 	
	 	
	 	public List<Claim> claimRaisedByMe(String EmpId){
	 		
	 		return 
	 	}
}
