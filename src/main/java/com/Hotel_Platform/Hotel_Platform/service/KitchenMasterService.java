package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.KitchenMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.entity.KitchenMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.KitchenMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class KitchenMasterService {

	@Autowired
	private KitchenMasterRepository kitchenMasterRepo;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	private KitchenMasterDTO mapToDTO(KitchenMaster kitchenMaster) {
		KitchenMasterDTO dto = new KitchenMasterDTO();
		
		dto.setId(kitchenMaster.getId());
		dto.setKitchenName(kitchenMaster.getKitchenName());
		dto.setPrinterName(kitchenMaster.getPrinterName());
		
		TenantSummaryDTO tenantDto = new TenantSummaryDTO();
		
		tenantDto.setId(kitchenMaster.getTenant().getId());
		tenantDto.setName(kitchenMaster.getTenant().getName());
		dto.setTenant(tenantDto);
		return dto;
		
	}
	
	
	public KitchenMasterDTO createKitchen(KitchenMasterDTO kitchenMasterDto, Long tenantId) {
		
		Tenant tenant = tenantRepository.findById(tenantId)
				.orElseThrow(() -> new CustomException(" Tenant Not Found ", HttpStatus.NOT_FOUND));
		
		KitchenMaster entity = new KitchenMaster();
		entity.setKitchenName(kitchenMasterDto.getKitchenName());
		entity.setPrinterName(kitchenMasterDto.getPrinterName());
		entity.setTenant(tenant);
		
		Optional<KitchenMaster> existing =
				kitchenMasterRepo.findByKitchenNameIgnoreCaseAndTenant_Id(kitchenMasterDto.getKitchenName(), tenantId);
		
		if(existing.isPresent()) {
			throw new CustomException(" Kitchen Master Already Exists with this Tenant Id", HttpStatus.BAD_REQUEST);
		}
		
		KitchenMaster saved = kitchenMasterRepo.save(entity);
		return mapToDTO(saved);
		
	}
	
	
	public List<KitchenMasterDTO> getAllKitchenByTenant(Long tenantId){
		
		if(!tenantRepository.existsById(tenantId)) {
			throw new CustomException(" Tenant Not Found ", HttpStatus.NOT_FOUND);
		}
		
		List<KitchenMaster> floorMaster = kitchenMasterRepo.findByTenant_Id(tenantId);
		return floorMaster.stream()
				.map(this::mapToDTO)
				.toList();
			
	}
}
