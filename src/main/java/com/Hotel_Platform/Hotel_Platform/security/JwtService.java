package com.Hotel_Platform.Hotel_Platform.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;





import javax.crypto.SecretKey;


import io.jsonwebtoken.security.Keys;

//@Service
//public class JwtService {
//
//    // Must be at least 256 bits (32 characters)
//    private static final String SECRET_KEY = "my-ultra-secret-key-32-char-min!";
//    private static final long EXPIRY = 1000 * 60 * 60 * 24; // 24 hours
//
//    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//
//    public String generateToken(User user) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("tenantId", user.getTenant().getId());
//        claims.put("role", user.getRole().getName());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(user.getEmail())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public SecretKey getSecretKey() {
//        return key;
//    }
//}


@Service
public class JwtService {

    private static final String SECRET_KEY = "my-ultra-secret-key-32-char-min!";
    private static final long EXPIRY = 1000 * 60 * 60 * 24; // 24 hours

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tenantId", user.getTenant().getId());
        //claims.put("role", user.getRole().getName()); // e.g. "Admin"
        claims.put("role", user.getRole().getName().toUpperCase());


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    // 🔐 NEW: Token generation for Tenant-based Admin login
    public String generateTokenWithTenant(Tenant tenant) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tenantId", tenant.getId());
        claims.put("tenantCode", tenant.getTenantCode());
        claims.put("role", "ADMIN");

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(tenant.getContactEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public SecretKey getSecretKey() {
        return key;
    }
}
