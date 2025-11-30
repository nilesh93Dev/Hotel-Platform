package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.entity.Role;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.RoleRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class RoleService {

	
	@Autowired
	public RoleRepository roleRepository;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	
//	public Role createRole(Role role, Long tenantId) {
//		
//		Tenant tenant = tenantRepository.findById(tenantId).orElseThrow();
//		role.setTenant(tenant);
//		return roleRepository.save(role);
//	}
//	
//	public List<Role> getRolesByTenant(Long tenantId){
//		
//		return roleRepository.findByTenantId(tenantId);
//		
//	}
	
	
	public List<Role> getAllRolesSortedById() {
        return roleRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }


}
