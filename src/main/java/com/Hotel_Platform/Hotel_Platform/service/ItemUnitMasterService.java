package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.ItemUnitDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.entity.ItemUnitMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.ItemUnitMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class ItemUnitMasterService {
	
	
	@Autowired
	private ItemUnitMasterRepository itemUnitRepo;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	private ItemUnitDTO mapToDTO(ItemUnitMaster unit) {
	    ItemUnitDTO dto = new ItemUnitDTO();
	    dto.setId(unit.getId());
	    dto.setUnitName(unit.getUnitName());

	    TenantSummaryDTO tenantDto = new TenantSummaryDTO();
	    tenantDto.setId(unit.getTenant().getId());
	    tenantDto.setName(unit.getTenant().getName());

	    dto.setTenant(tenantDto);
	    return dto;
	}
	
	
	
	public ItemUnitDTO createUnit(ItemUnitDTO itemUnitDto, Long tenantId) {
	    Tenant tenant = tenantRepository.findById(tenantId)
	            .orElseThrow(() -> new CustomException("Tenant Not found", HttpStatus.NOT_FOUND));

	    ItemUnitMaster entity = new ItemUnitMaster();
	    entity.setUnitName(itemUnitDto.getUnitName());
	    entity.setTenant(tenant);

	    Optional<ItemUnitMaster> existing =
	    		itemUnitRepo.findByUnitNameIgnoreCaseAndTenant_Id(itemUnitDto.getUnitName(), tenantId);

	    if (existing.isPresent()) {
	        throw new CustomException("Item Unit Already Exists with this Tenant Id", HttpStatus.BAD_REQUEST);
	    }

	    ItemUnitMaster saved = itemUnitRepo.save(entity);
	    return mapToDTO(saved);  // ✅ Only DTO returned
	}
	
	
	 public List<ItemUnitDTO> getAllUnitByTenant(Long tenantId) {
	    	if(!tenantRepository.existsById(tenantId)) {
	    		throw new CustomException(" Tenant Not Found ", HttpStatus.NOT_FOUND);
	    	}
	    		List<ItemUnitMaster> unitMaster = itemUnitRepo.findByTenant_Id(tenantId);
	    		return unitMaster.stream()
	    				.map(this::mapToDTO)
	    				.toList();
	    }


}
