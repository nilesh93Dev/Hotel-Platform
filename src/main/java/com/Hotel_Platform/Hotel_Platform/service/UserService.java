package com.Hotel_Platform.Hotel_Platform.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.RoleDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.dto.UserCreateRequest;
import com.Hotel_Platform.Hotel_Platform.dto.UserDTO;
import com.Hotel_Platform.Hotel_Platform.dto.UserPermissionDTO;
import com.Hotel_Platform.Hotel_Platform.entity.Permission;
import com.Hotel_Platform.Hotel_Platform.entity.Role;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.entity.User;
import com.Hotel_Platform.Hotel_Platform.repository.RoleRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;
import com.Hotel_Platform.Hotel_Platform.repository.UserRepository;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create user under specific tenant
    public User createUser(User user, Long tenantId) {

        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        Role role = roleRepository.findById(user.getRole().getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setTenant(tenant);
        user.setRole(role);

        // 🔐 encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

//    // Get all users of tenant
//    public List<User> getUsersByTenant(Long tenantId) {
//        return userRepository.findByTenantId(tenantId);
//    }
    
    
    
    public List<UserDTO> getUsersByTenant(Long tenantId) {
        List<User> users = userRepository.findByTenantId(tenantId);

        // Convert entity list to DTO list
        return users.stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUserName(),
                        user.getEmail(),
                        null, // password hide for security
                        new RoleDTO(user.getRole().getId(), user.getRole().getName(), null),
                        new TenantSummaryDTO(user.getTenant().getId(), user.getTenant().getName()),
                        user.getPermissions().stream()
                            .map(p -> new UserPermissionDTO(p.getId(), p.getName()))
                            .toList()
                ))
                .toList();
    }
}



//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Autowired
//    private TenantRepository tenantRepo;
//    
//    @Autowired
//	public RoleRepository roleRepository;
//
//
//    public List<User> getUsers(Long tenantId) {
//        return userRepo.findByTenantId(tenantId);
//    }
//
//}
