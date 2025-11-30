package com.Hotel_Platform.Hotel_Platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.entity.Role;
import com.Hotel_Platform.Hotel_Platform.entity.User;
import com.Hotel_Platform.Hotel_Platform.repository.RoleRepository;
import com.Hotel_Platform.Hotel_Platform.repository.UserRepository;

@Service
public class AuthService {
	

	    @Autowired
	    private UserRepository userRepo;

//	    public User authenticate(String email, String password) {
//	        User user = userRepo.findByEmail(email)
//	            .orElseThrow(() -> new RuntimeException("Invalid email"));
//
//	        if (!user.getPassword().equals(password)) {
//	            throw new RuntimeException("Invalid password");
//	        }
//
//	        return user;
//	    }
	    
	    
	    @Autowired
		public RoleRepository roleRepository;

	    public User authenticate(String email, String password, String roleName) {
	        Role role = roleRepository.findByName(roleName)
	            .orElseThrow(() -> new RuntimeException("Invalid role"));

	        User user = userRepo.findByEmailAndRole(email, role)
	            .orElseThrow(() -> new RuntimeException("Invalid email or role"));

	        if (!user.getPassword().equals(password)) {
	            throw new RuntimeException("Invalid password");
	        }

	        return user;
	    }
	}
	


