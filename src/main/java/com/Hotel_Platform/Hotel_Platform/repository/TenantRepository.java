package com.Hotel_Platform.Hotel_Platform.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

	boolean existsByTenantCode(String tenantCode);

//	Optional<Tenant> findByContactEmail(String contactEmail);
	Optional<Tenant> findByContactEmail(String email);


	

}
