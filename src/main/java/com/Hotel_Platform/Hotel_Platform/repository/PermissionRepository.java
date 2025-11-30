package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

	List<Permission> findByTenantId(Long tenantId);

}
