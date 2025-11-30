package com.Hotel_Platform.Hotel_Platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.service.TenantService;
import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.TENANT_MASTER;

@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("Hotel/tenants")
@RequestMapping(TENANT_MASTER)
public class TenantController {
	
	
	@Autowired
	private TenantService tenantService;
	
	@PostMapping
	public Tenant createTenant(@RequestBody Tenant tenant) {
		Tenant crTenant = tenantService.createTenant(tenant);
		
		return crTenant;
	}

}
