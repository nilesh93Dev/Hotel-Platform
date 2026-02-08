package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.FoodPackageMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.entity.FoodPackageMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.enums.GstType;
import com.Hotel_Platform.Hotel_Platform.repository.FoodPackageMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;


@Service
public class FoodPackageMasterService {
	
	@Autowired
	private FoodPackageMasterRepository foodPackageMasterRepo;
	
	 @Autowired
	    private TenantRepository tenantRepository;
	 
	 private FoodPackageMasterDTO mapToDTO(FoodPackageMaster entity) {
		 FoodPackageMasterDTO dto = new FoodPackageMasterDTO();
		 
		 dto.setId(entity.getId());
		 dto.setFoodName(entity.getFoodName());
		 dto.setRate(entity.getRate());
		 dto.setGstPercentage(entity.getGstPercentage());
		 dto.setGstType(entity.getGstType().name());
		 
		 Tenant tenant = entity.getTenant();
		 if(tenant != null) {
			 
			 TenantSummaryDTO tenantDto = new TenantSummaryDTO();
			 tenantDto.setId(tenant.getId());
			 tenantDto.setName(tenant.getName());
			 dto.setTenant(tenantDto);
			 
		 }
		 
		 return dto;
		 }
	 
	 // Post Api
	 public FoodPackageMasterDTO createFoodPackage(FoodPackageMasterDTO dto, Long tenantId) {
		 Tenant tenant = tenantRepository.findById(tenantId)
				 .orElseThrow(() -> new CustomException(" Tenant not Found ", HttpStatus.NOT_FOUND));
		 
		 Optional<FoodPackageMaster> existing =
				 foodPackageMasterRepo.findByFoodNameIgnoreCaseAndTenant_Id(dto.getFoodName(), tenantId);
				 
	 
	 if(existing.isPresent()) {
		 throw new CustomException(" Food Package Already Exists with this Tenant Id ", HttpStatus.BAD_REQUEST);
	 }
	 
	 FoodPackageMaster entity = new FoodPackageMaster();
	 entity.setFoodName(dto.getFoodName());
	 entity.setRate(dto.getRate());
	 entity.setGstPercentage(dto.getGstPercentage());
	 entity.setGstType(GstType.valueOf(dto.getGstType().toUpperCase()));
	 entity.setTenant(tenant);
	 
	 FoodPackageMaster saved = foodPackageMasterRepo.save(entity);
	 return mapToDTO(saved);
	 
		 
	 
	 }
	 
	 
	 public List<FoodPackageMasterDTO> getAllFoodPackagesByTenant(Long tenantId){
		 Tenant tenant = tenantRepository.findById(tenantId)
				 .orElseThrow(() -> new CustomException(" Tenant not Found ", HttpStatus.NOT_FOUND));
		 
		 List<FoodPackageMaster> packages = foodPackageMasterRepo.findByTenant_Id(tenantId);
		 
		 return packages.stream()
				 .map(this::mapToDTO)
				 .collect(Collectors.toList());
	 }

}
