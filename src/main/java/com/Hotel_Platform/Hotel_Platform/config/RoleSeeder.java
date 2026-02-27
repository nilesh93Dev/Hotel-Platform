package com.Hotel_Platform.Hotel_Platform.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Hotel_Platform.Hotel_Platform.entity.Role;
import com.Hotel_Platform.Hotel_Platform.repository.RoleRepository;

@Component
public class RoleSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        List<String> defaultRoles = List.of("ADMIN", "USER", "WAITER", "Cleaner");
        for (String roleName : defaultRoles) {
            if (!roleRepository.existsByName(roleName)) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
        }
    }
}
