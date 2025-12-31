package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.entity.ItemUnitMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.ItemUnitMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class ItemUnitMasterService {
	
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private ItemUnitMasterRepository itemUnitRepo;
	
	 public ItemUnitMaster createUnit(ItemUnitMaster itemUnit, Long tenantId) {
	        Tenant tenant = tenantRepository.findById(tenantId)
	            .orElseThrow(() -> new CustomException("Tenant not found", HttpStatus.NOT_FOUND));

	        itemUnit.setTenant(tenant);

	        Optional<ItemUnitMaster> existing =
	            itemUnitRepo.findByUnitNameIgnoreCaseAndTenant_Id(itemUnit.getUnitName(), tenantId);

	        if (existing.isPresent()) {
	            throw new CustomException("Unit already exists with this UnitName for this tenant", HttpStatus.BAD_REQUEST);
	        }

	        try {
	            return itemUnitRepo.save(itemUnit);
	        } catch (Exception ex) {
	            throw new CustomException("Failed to save Unit: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 
	 // GET ALL API
	
	public List<ItemUnitMaster> getAllUnitsByTenant(Long tenantId){
		if(!tenantRepository.existsById(tenantId)) {
			throw new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND);
		}
		
		return itemUnitRepo.findByTenant_Id(tenantId);
	}
	
	// UPDATE API
	
	public ItemUnitMaster updateUnit(Long id, Long tenantId, ItemUnitMaster updatedUnit) {
		
		Tenant tenant = tenantRepository.findById(tenantId)
				.orElseThrow(() -> new CustomException(" Tenant Not Found", HttpStatus.NOT_FOUND));
	
	
	ItemUnitMaster existingUnit = itemUnitRepo.findById(id)
			.orElseThrow(() -> new CustomException(" Unit not Found Given This Tenant", HttpStatus.NOT_FOUND));

	if(!existingUnit.getTenant().getId().equals(tenantId)) {
		throw new CustomException(" Uint doest belongs to spefic Tenant",HttpStatus.BAD_REQUEST);
	}
	
	Optional<ItemUnitMaster> duplicate = itemUnitRepo.findByUnitNameIgnoreCaseAndTenant_Id(updatedUnit.getUnitName(), tenantId);

	if(duplicate.isPresent() && !duplicate.get().getId().equals(id)) {
		throw new CustomException(" Another unit already exists with the same name", HttpStatus.BAD_REQUEST);
	}
	
	existingUnit.setUnitName(updatedUnit.getUnitName());
     
	try {
		
		return itemUnitRepo.save(existingUnit);
	} catch(Exception ex) {
		 throw new CustomException("Failed to update Unit: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	}
	
	
	
	
	// DELETE METHOD
	
	public void deleteUnit(Long id, Long tenantId) {
		ItemUnitMaster unit = itemUnitRepo.findByIdAndTenant_Id(id, tenantId)
            .orElseThrow(() -> new RuntimeException("Unit Type not found or ShopId mismatch"));
		itemUnitRepo.delete(unit);
    }


	//public void deleteUnit(Long id, Long tenantId) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
