package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.DishHeadDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.entity.DishHeadMaster;
import com.Hotel_Platform.Hotel_Platform.entity.DishTypeMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.DishHeadMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.DishTypeMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class DishHeadMasterService {
	
	@Autowired
    private DishHeadMasterRepository dishHeadRepo;

    @Autowired
    private DishTypeMasterRepository dishTypeRepo;

    @Autowired
    private TenantRepository tenantRepo;

    private DishHeadDTO mapToDTO(DishHeadMaster entity) {
        DishHeadDTO dto = new DishHeadDTO();
        dto.setId(entity.getId());
        dto.setDishHeadName(entity.getDishHeadName());
        dto.setDishTypeId(entity.getDishType().getId());

        TenantSummaryDTO tenantDto = new TenantSummaryDTO();
        tenantDto.setId(entity.getTenant().getId());
        tenantDto.setName(entity.getTenant().getName());

        dto.setTenant(tenantDto);
        return dto;
    }

    public DishHeadDTO createDishHead(DishHeadDTO dto, Long tenantId) {
        Tenant tenant = tenantRepo.findById(tenantId)
                .orElseThrow(() -> new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND));

        DishTypeMaster dishType = dishTypeRepo.findById(dto.getDishTypeId())
                .orElseThrow(() -> new CustomException("Dish Type Not Found", HttpStatus.NOT_FOUND));

        DishHeadMaster entity = new DishHeadMaster();
        entity.setDishHeadName(dto.getDishHeadName());
        entity.setDishType(dishType);
        entity.setTenant(tenant);

        DishHeadMaster saved = dishHeadRepo.save(entity);
        return mapToDTO(saved);
    }

    public List<DishHeadDTO> getAllDishHeadsByTenant(Long tenantId) {
        if (!tenantRepo.existsById(tenantId)) {
            throw new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND);
        }

        return dishHeadRepo.findByTenant_Id(tenantId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}



