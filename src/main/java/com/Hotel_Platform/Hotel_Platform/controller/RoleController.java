package com.Hotel_Platform.Hotel_Platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Platform.Hotel_Platform.entity.Role;
import com.Hotel_Platform.Hotel_Platform.service.RoleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Hotel/roles")
public class RoleController {
	
	
	@Autowired
	private RoleService roleService;
	
//	@PostMapping
//	public Role createRole(@RequestBody Role role , @RequestHeader("tenantId") Long tenantId) {
//		Role crRole = roleService.createRole(role, tenantId);
//		
//		return crRole;
//	}
//	
//	
//	@GetMapping
//	public List<Role> getRoles(@RequestAttribute("tenantId") Long tenantId){
//		
//		return roleService.getRolesByTenant(tenantId);
//	}
	
	
	@GetMapping()
	public List<Role> getAllRoles() {
	    return roleService.getAllRolesSortedById();
	}
//	
	

}
