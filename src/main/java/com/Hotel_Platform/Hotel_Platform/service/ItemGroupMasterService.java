package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.ItemGroupDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
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
	

	
	private ItemGroupDTO mapToDTO(ItemGroupMaster group) {
	    ItemGroupDTO dto = new ItemGroupDTO();
	    dto.setId(group.getId());
	    dto.setGroupName(group.getGroupName());

	    TenantSummaryDTO tenantDto = new TenantSummaryDTO();
	    tenantDto.setId(group.getTenant().getId());
	    tenantDto.setName(group.getTenant().getName());

	    dto.setTenant(tenantDto);
	    return dto;
	}

	
	public ItemGroupDTO createGroup(ItemGroupDTO itemGroupDto, Long tenantId) {
	    Tenant tenant = tenantRepository.findById(tenantId)
	            .orElseThrow(() -> new CustomException("Tenant Not found", HttpStatus.NOT_FOUND));

	    ItemGroupMaster entity = new ItemGroupMaster();
	    entity.setGroupName(itemGroupDto.getGroupName());
	    entity.setTenant(tenant);

	    Optional<ItemGroupMaster> existing =
	            itemGroupRepository.findByGroupNameIgnoreCaseAndTenant_Id(itemGroupDto.getGroupName(), tenantId);

	    if (existing.isPresent()) {
	        throw new CustomException("Item Group Already Exists with this Tenant Id", HttpStatus.BAD_REQUEST);
	    }

	    ItemGroupMaster saved = itemGroupRepository.save(entity);
	    return mapToDTO(saved);  // ✅ Only DTO returned
	}


	
	
	public List<ItemGroupDTO> getAllGroupsByTenant(Long tenantId) { 
		if (!tenantRepository.existsById(tenantId)) {
			throw new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND);
			}
		
		List<ItemGroupMaster> groups = itemGroupRepository.findByTenant_Id(tenantId);
		return groups.stream().map(this::mapToDTO).toList();
		}
	}
	
	
	

