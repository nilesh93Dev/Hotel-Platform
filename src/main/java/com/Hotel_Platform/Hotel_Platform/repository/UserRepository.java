package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.Role;
import com.Hotel_Platform.Hotel_Platform.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	List<User> findByTenantId(Long tenantId);

	//Object findByEmail(String email);
	Optional<User> findByEmail(String email);

	 Optional<User> findByEmailAndRole(String email, Role role);
	 
	 
//	 Optional<User> findByEmail(String email); 
	 Optional<User> findByIdAndTenant_Id(Long id, Long tenantId);


}

