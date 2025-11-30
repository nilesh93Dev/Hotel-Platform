package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.entity.ItemGroupMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.ItemGroupMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class ItemGroupMasterService {
	
	@Autowired
	private ItemGroupMasterRepository itemGroupRepository;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	public ItemGroupMaster createGroup(ItemGroupMaster itemGroup, Long tenantId) {
		
		Tenant tenant = tenantRepository.findById(tenantId)
				.orElseThrow(() -> new CustomException("Tenant Not found", HttpStatus.NOT_FOUND));
		
		itemGroup.setTenant(tenant);
	
	
	Optional<ItemGroupMaster> existing = itemGroupRepository.findByGroupNameIgnoreCaseAndTenantId(itemGroup.getGroupName(), tenantId);

	if(existing.isPresent()) {
		throw new CustomException(" Item Gropu Already Exists with this Tenant Id", HttpStatus.BAD_REQUEST);
	}
	try {
		return itemGroupRepository.save(itemGroup);
	} catch (Exception ex) {
		throw new CustomException(" Failed To Save Item Group" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	public List<ItemGroupMaster> getAllGroupsByTenant(Long tenantId){
		
		if(!tenantRepository.existsById(tenantId)) {
			throw new CustomException(" Tenant Not Found ", HttpStatus.NOT_FOUND);
		}
		
		return itemGroupRepository.findByTenantId(tenantId);
		
	}
	
}
