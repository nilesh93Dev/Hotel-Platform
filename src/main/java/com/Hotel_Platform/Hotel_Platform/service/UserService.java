package com.Hotel_Platform.Hotel_Platform.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.UserCreateRequest;
import com.Hotel_Platform.Hotel_Platform.entity.Role;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.entity.User;
import com.Hotel_Platform.Hotel_Platform.repository.RoleRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;
import com.Hotel_Platform.Hotel_Platform.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TenantRepository tenantRepo;
    
    @Autowired
	public RoleRepository roleRepository;

//    public User createUser(UserCreateRequest request, Long tenantId) {
//        Tenant tenant = tenantRepo.findById(tenantId).orElseThrow();
//        Role role = roleRepository.findById(request.getRoleId()).orElseThrow();
//
//        if (!role.getTenant().getId().equals(tenantId)) throw new RuntimeException("Role mismatch");
//
//        User user = new User();
//        user.setUserName(request.getUserName());
//        user.setEmail(request.getEmail());
//        user.setPassword(request.getPassword());
//        user.setRole(role);
//        user.setTenant(tenant);
//
//        return userRepo.save(user);
//    }

    public List<User> getUsers(Long tenantId) {
        return userRepo.findByTenantId(tenantId);
    }
}
