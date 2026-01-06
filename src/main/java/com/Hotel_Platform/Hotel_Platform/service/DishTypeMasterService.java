package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.DishTypeDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.entity.DishTypeMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.DishTypeMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class DishTypeMasterService {
	
	@Autowired
	private DishTypeMasterRepository dishTypeMasterRepo;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	private DishTypeDTO mapToDTO(DishTypeMaster dishType) {
		DishTypeDTO dto = new DishTypeDTO();
		
		dto.setId(dishType.getId());
		dto.setDishTypeName(dishType.getDishTypeName());
		
		TenantSummaryDTO tenantDto = new TenantSummaryDTO();
		tenantDto.setId(dishType.getTenant().getId());
		tenantDto.setName(dishType.getTenant().getName());
		
		dto.setTenant(tenantDto);
		return dto;
		
	}
	
	
	public DishTypeDTO createDishType(DishTypeDTO dishTypeDto, Long tenantId) {
	    Tenant tenant = tenantRepository.findById(tenantId)
	            .orElseThrow(() -> new CustomException("Tenant Not found", HttpStatus.NOT_FOUND));

	    DishTypeMaster entity = new DishTypeMaster();
	    entity.setDishTypeName(dishTypeDto.getDishTypeName());
	    entity.setTenant(tenant);

	    Optional<DishTypeMaster> existing =
	    		dishTypeMasterRepo.findByDishTypeNameIgnoreCaseAndTenant_Id(dishTypeDto.getDishTypeName(), tenantId);

	    if (existing.isPresent()) {
	        throw new CustomException("Dish Type Already Exists with this Tenant Id", HttpStatus.BAD_REQUEST);
	    }

	    DishTypeMaster saved = dishTypeMasterRepo.save(entity);
	    return mapToDTO(saved);
	}


	public List<DishTypeDTO> getAllDishTypesByTenant(Long tenantId) {
		if(!tenantRepository.existsById(tenantId)) {
			throw new CustomException(" Tenant not Found ", HttpStatus.NOT_FOUND);
		}
		
		List<DishTypeMaster> dishTypes = dishTypeMasterRepo.findByTenant_Id(tenantId);
		return dishTypes.stream()
				.map(this::mapToDTO).toList();
				
				
	}


}
