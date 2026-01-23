package com.Hotel_Platform.Hotel_Platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Platform.Hotel_Platform.dto.PendingTenant;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;
import com.Hotel_Platform.Hotel_Platform.service.EmailService;
import com.Hotel_Platform.Hotel_Platform.service.TenantService;
import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.TENANT_MASTER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping(TENANT_MASTER)
public class TenantController {

    private Map<String, PendingTenant> pendingTenantStore = new HashMap<>();

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private EmailService emailService;

    // Step 1: Request tenant creation
    //@PostMapping("/request")
//    @PostMapping
//    public ResponseEntity<String> requestTenantCreation(@RequestBody PendingTenant pendingTenant) {
//        String token = UUID.randomUUID().toString();
//        pendingTenant.setVerificationToken(token);
//        pendingTenant.setVerified(false);
//
//        pendingTenantStore.put(token, pendingTenant);
//
//        // Send email to admin
//        emailService.sendVerificationLink("nileshkumarpatna93@gmail.com", token);
//
//        return ResponseEntity.ok("Verification link sent to admin email.");
//    }
    
    
    @PostMapping
    public ResponseEntity<String> requestTenantCreation(@RequestBody PendingTenant pendingTenant) throws IOException {
        String token = UUID.randomUUID().toString();
        pendingTenant.setVerificationToken(token);
        pendingTenant.setVerified(false);

        pendingTenantStore.put(token, pendingTenant);

        // Send email to admin
		emailService.sendVerificationLink("nileshkumarpatna93@gmail.com", token);
		return ResponseEntity.ok("Verification link sent to admin email.");
    }


    // Step 2: Verify tenant
    @GetMapping("/verify")
    public ResponseEntity<String> verifyTenant(@RequestParam String token) {
        PendingTenant pending = pendingTenantStore.get(token);
        if (pending == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid or expired token.");
        }

        Tenant tenant = new Tenant();
        tenant.setTenantCode(pending.getTenantCode());
        tenant.setName(pending.getName());
        tenant.setContactEmail(pending.getContactEmail());
        tenant.setGstNumber(pending.getGstNumber());
        tenant.setPhoneNumber(pending.getPhoneNumber());
        tenant.setAddress(pending.getAddress());
        tenant.setPassword(pending.getPassword());

        tenantRepository.save(tenant);
        pendingTenantStore.remove(token);

        return ResponseEntity.ok("Tenant verified and created successfully.");
    }
}


//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping(TENANT_MASTER)
//public class TenantController {
//	
//	
//	@Autowired
//	private TenantService tenantService;
//	
//	@PostMapping
//	public Tenant createTenant(@RequestBody Tenant tenant) {
//		Tenant crTenant = tenantService.createTenant(tenant);
//		
//		return crTenant;
//	}


