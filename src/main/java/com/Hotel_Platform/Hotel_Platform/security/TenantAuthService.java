package com.Hotel_Platform.Hotel_Platform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class TenantAuthService {

    @Autowired
    private TenantRepository tenantRepository;

    public Tenant authenticate(String email, String password) {
        System.out.println("🔐 Attempting login for: " + email);

        Tenant tenant = tenantRepository.findByContactEmail(email)
            .orElseThrow(() -> {
                System.out.println("❌ No tenant found with email: " + email);
                return new RuntimeException("Invalid email");
            });

        System.out.println("✅ Tenant found: " + tenant.getName());
        System.out.println("🔍 Stored password: " + tenant.getPassword());
        System.out.println("🔍 Provided password: " + password);

        if (!tenant.getPassword().equals(password)) {
            System.out.println("❌ Password mismatch for tenant: " + tenant.getName());
            throw new RuntimeException("Invalid password");
        }

        System.out.println("✅ Tenant authenticated: " + tenant.getName() + " (ID: " + tenant.getId() + ")");
        return tenant;
    }}

