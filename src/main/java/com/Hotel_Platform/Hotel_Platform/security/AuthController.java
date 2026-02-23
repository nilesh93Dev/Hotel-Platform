package com.Hotel_Platform.Hotel_Platform.security;

import java.util.Date;

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

import io.jsonwebtoken.Jwts;

//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping("Hotel/auth")
//public class AuthController {
//	
//	
//	@Autowired
//	private AuthService authService;
//	
//	@Autowired
//    private TenantAuthService tenantAuthService;
//	
//	@Autowired
//	private JwtService jwtService;
//
//
//	
//	
//	@PostMapping("/admin-login")
//	public ResponseEntity<?> adminLogin(@RequestBody LoginRequest request) {
//	    Tenant tenant = tenantAuthService.authenticate(request.getEmail(), request.getPassword());
//	    String token = jwtService.generateTokenWithTenant(tenant);
//
//	    JwtResponse response = new JwtResponse(
//	        token,
//	        tenant.getId(),
//	        tenant.getName(),
//	        tenant.getContactEmail()
//	    );
//
//	    return ResponseEntity.ok(response);
//	}


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

    @PostMapping("/admin-login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginRequest request) {
        // Authenticate tenant (Admin credentials)
        Tenant tenant = tenantAuthService.authenticate(request.getEmail(), request.getPassword());

        // ✅ Generate JWT token with role + tenant info
        String token = Jwts.builder()
                .setSubject(request.getEmail())
                .claim("role", "ADMIN")   // match DB role exactly
                .claim("tenantId", tenant.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiry
                .signWith(jwtService.getSecretKey())
                .compact();

        JwtResponse response = new JwtResponse(
                token,
                tenant.getId(),
                tenant.getName(),
                tenant.getContactEmail()
        );

        return ResponseEntity.ok(response);
    }
    
    
    
 // ✅ User login (new)
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest request) {
        // Authenticate user (via AuthService)
        User user = authService.authenticate(request.getEmail(), request.getPassword());

        // Generate JWT token with role + tenant info
        String token = jwtService.generateToken(user);

        JwtResponse response = new JwtResponse(
                token,
                user.getTenant().getId(),
                user.getTenant().getName(),
                user.getEmail()
        );

        return ResponseEntity.ok(response);
    }  
    
    
}





