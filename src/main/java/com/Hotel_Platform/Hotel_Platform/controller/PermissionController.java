package com.Hotel_Platform.Hotel_Platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Platform.Hotel_Platform.entity.Permission;

import com.Hotel_Platform.Hotel_Platform.service.PermissionService;
import com.Hotel_Platform.Hotel_Platform.service.TenantService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Hotel/permission")
public class PermissionController {
	
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private TenantService tenantService;
	
	
	@PostMapping
	public Permission createPermission(@RequestBody Permission permission, @RequestHeader("tenantId") Long tenantId) {
		return permissionService.createPermission(permission, tenantId);
		
				
	}
	
	@GetMapping
	public List<Permission> getPermissions(@RequestAttribute("tenantId") Long tenantId){
		
		return permissionService.getPermissionsByTenant(tenantId);
	}
	
	
	

}
