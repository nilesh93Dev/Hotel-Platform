package com.Hotel_Platform.Hotel_Platform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Platform.Hotel_Platform.dto.JwtResponse;
import com.Hotel_Platform.Hotel_Platform.dto.LoginRequest;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.entity.User;
import com.Hotel_Platform.Hotel_Platform.service.AuthService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("Hotel/auth")
public class AuthController {
	
	
	@Autowired
	private AuthService authService;
	
	@Autowired
    private TenantAuthService tenantAuthService;
	
	@Autowired
	private JwtService jwtService;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//        User user = authService.authenticate(request.getEmail(), request.getPassword());
//
//        String token = jwtService.generateToken(user); // includes tenantId in claims
//        return ResponseEntity.ok(new JwtResponse(token));
//    }
	
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//	    User user = authService.authenticate(
//	        request.getEmail(),
//	        request.getPassword(),
//	        request.getRole()
//	    );
//
//	    String token = jwtService.generateToken(user); // includes tenantId, role, userId
//	    return ResponseEntity.ok(new JwtResponse(token));
//	}
	
	
//	@PostMapping("/admin-login")
//	public ResponseEntity<?> adminLogin(@RequestBody LoginRequest request) {
//	    Tenant tenant = tenantAuthService.authenticate(request.getEmail(), request.getPassword());
//	    String token = jwtService.generateTokenWithTenant(tenant);
//	    return ResponseEntity.ok(new JwtResponse(token));
//	}
	
	
	@PostMapping("/admin-login")
	public ResponseEntity<?> adminLogin(@RequestBody LoginRequest request) {
	    Tenant tenant = tenantAuthService.authenticate(request.getEmail(), request.getPassword());
	    String token = jwtService.generateTokenWithTenant(tenant);

	    JwtResponse response = new JwtResponse(
	        token,
	        tenant.getId(),
	        tenant.getName(),
	        tenant.getContactEmail()
	    );

	    return ResponseEntity.ok(response);
	}


}

