package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.entity.Permission;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.PermissionRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class PermissionService {

	
	@Autowired
	private PermissionRepository permissionRepo;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	public Permission createPermission(Permission permission , Long tenantId) {
		
		Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
		permission.setTenant(tenant);
		return permissionRepo.save(permission);
	}
	
	
	public List<Permission> getPermissionsByTenant(Long tenantId){
		
		return permissionRepo.findByTenantId(tenantId);
	}
	
	
	
}
