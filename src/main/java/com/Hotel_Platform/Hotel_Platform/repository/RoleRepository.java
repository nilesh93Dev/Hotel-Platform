package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.entity.Role;

@Service
public interface RoleRepository extends JpaRepository<Role, Long> {

//	List<Role> findByTenantId(Long tenantId);

//	boolean existsByName(String roleName);
	Optional<Role> findByName(String name); // 👈 This is the missing method
    boolean existsByName(String name);
	

}
