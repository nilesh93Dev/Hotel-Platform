package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.FloorMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.entity.FloorMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.FloorMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class FloorMasterService {
    
    @Autowired
    private FloorMasterRepository floorMasterRepo;
    
    @Autowired
    private TenantRepository tenantRepository;
    
//    public FloorMaster createFloor(FloorMaster floor, Long tenantId) {
//        Tenant tenant = tenantRepository.findById(tenantId)
//                .orElseThrow(() -> new CustomException("Tenant Not found", HttpStatus.NOT_FOUND));
//        
//        Optional<FloorMaster> existing =
//                floorMasterRepo.findByFloorNameIgnoreCaseAndTenant_Id(floor.getFloorName(), tenantId);
//        
//        if(existing.isPresent()) {
//            throw new CustomException("Floor Name Already exists with this Tenant Id", HttpStatus.BAD_REQUEST);
//        }
//        
//        floor.setTenant(tenant);
//        
//        return floorMasterRepo.save(floor);
//    }
    
    
    private FloorMasterDTO mapToDTO(FloorMaster floor) {
    	FloorMasterDTO dto = new FloorMasterDTO();
    	
    	dto.setId(floor.getId());
    	dto.setFloorName(floor.getFloorName());
    	
    	TenantSummaryDTO tenantDto = new TenantSummaryDTO();
    	tenantDto.setId(floor.getTenant().getId());
    	tenantDto.setName(floor.getTenant().getName());
    	
    	dto.setTenant(tenantDto);
    	return dto;
    	
    	
    }
    
    
    public FloorMasterDTO createFloor(FloorMasterDTO floorDto, Long tenantId) {
    	Tenant tenant = tenantRepository.findById(tenantId)
    			.orElseThrow(() -> new CustomException(" Tenant not Found " , HttpStatus.NOT_FOUND));
    	
    	FloorMaster entity = new FloorMaster();
    	entity.setFloorName(floorDto.getFloorName());
    	entity.setTenant(tenant);
    	
    	Optional<FloorMaster> existing =
    			floorMasterRepo.findByFloorNameIgnoreCaseAndTenant_Id(floorDto.getFloorName(), tenantId);
    			
    	if (existing.isPresent()) {
    	    throw new CustomException("Floor Master Already Exists with this Tenant Id", HttpStatus.BAD_REQUEST);
    	}

    	
    	FloorMaster saved = floorMasterRepo.save(entity);
    	return mapToDTO(saved);
    		
    }
    
    
    public List<FloorMasterDTO> getAllFloorByTenant(Long tenantId) {
    	if(!tenantRepository.existsById(tenantId)) {
    		throw new CustomException(" Tenant Not Found ", HttpStatus.NOT_FOUND);
    	}
    		List<FloorMaster> floorMaster = floorMasterRepo.findByTenant_Id(tenantId);
    		return floorMaster.stream()
    				.map(this::mapToDTO)
    				.toList();
    }
}

