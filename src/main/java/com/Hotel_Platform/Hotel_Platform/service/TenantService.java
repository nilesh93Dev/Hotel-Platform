package com.Hotel_Platform.Hotel_Platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class TenantService {
	
	@Autowired
	private TenantRepository tenantRepository;
	
	
	public Tenant createTenant(Tenant tenant) {
		
//		if(tenantRepository.existsByTenantCode(tenant.getTenantCode())) {
//			throw new RuntimeException(" Tenant Code Already exists");
//		}
		
		return tenantRepository.save(tenant);
		
		
	}

}
