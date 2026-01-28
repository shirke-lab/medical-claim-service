package com.approver.service;
import com.approver.dto.ClaimdetailsFinal;
import com.approver.repository.ApprovalRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApprovalService {

    private final RestTemplate restTemplate;
    private final ApprovalRepository aprepo;
    

        public ApprovalService(RestTemplate restTemplate, ApprovalRepository aprepo) {
        this.restTemplate = restTemplate;
        this.aprepo=aprepo;
    }
//below is the method doing api call for retriving data from claim service we need to provide token for authentication is claim service.
    
    public ClaimdetailsFinal getClaimDetailsfromClaimService(Long claimId) {
        // If your claim service requires authentication, set headers here
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpYXQiOjE3NjgzMTE4NjUsImV4cCI6MTc2ODMxNzg2NX0.VyuuMxXCQHveLXCWbbjg1X33BfgLamLgJUHOZngF380");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ClaimdetailsFinal> response = restTemplate.exchange(
                "http://localhost:8081/claims/getClaim/" + claimId,
                HttpMethod.GET,
                entity,
                ClaimdetailsFinal.class
        );

        ClaimdetailsFinal dto = response.getBody();

        if (dto != null) {
            System.out.println("ClaimId: " + dto.getClaimId());
            System.out.println("Amount: " + dto.getAmount());
            System.out.println("EmpId: " + dto.getEmpId());
            System.out.println("description: " + dto.getDescription());
            aprepo.save(dto);          
 
        } else {
            System.out.println("No claim details found for ID: " + claimId);
        }

        return dto;
    }
}

