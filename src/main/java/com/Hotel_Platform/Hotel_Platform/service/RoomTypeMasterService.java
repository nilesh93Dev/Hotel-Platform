package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.RoomTypeDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.entity.RoomTypeMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.RoomTypeMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class RoomTypeMasterService {
	
	@Autowired
	private RoomTypeMasterRepository roomTypeMasterRepo;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	private RoomTypeDTO mapToDTO(RoomTypeMaster roomTypeMaster) {
		
		RoomTypeDTO dto = new RoomTypeDTO();
		dto.setId(roomTypeMaster.getId());
		dto.setRoomType(roomTypeMaster.getRoomType());
		dto.setTotalRoom(roomTypeMaster.getTotalRoom());
		dto.setOccupancy(roomTypeMaster.getOccupancy());
		dto.setSingleRent(roomTypeMaster.getSingleRent());
		dto.setDoubleRent(roomTypeMaster.getDoubleRent());
		dto.setRoomTypeDescription(roomTypeMaster.getRoomTypeDescription());
		dto.setChannelId(roomTypeMaster.getChannelId());
		
		TenantSummaryDTO tenantDto = new TenantSummaryDTO();
		tenantDto.setId(roomTypeMaster.getTenant().getId());
		tenantDto.setName(roomTypeMaster.getTenant().getName());
		dto.setTenant(tenantDto);
		return dto;
		
		
	}
	
	
	public RoomTypeDTO createRoomType(RoomTypeDTO roomTypeDto, Long tenantId) {
		Tenant tenant = tenantRepository.findById(tenantId)
				.orElseThrow(() -> new CustomException(" Tenant Not Found ", HttpStatus.NOT_FOUND));
		
		RoomTypeMaster entity = new RoomTypeMaster();
		entity.setRoomType(roomTypeDto.getRoomType());
		entity.setTotalRoom(roomTypeDto.getTotalRoom());
		entity.setOccupancy(roomTypeDto.getOccupancy());
		entity.setSingleRent(roomTypeDto.getSingleRent());
		entity.setDoubleRent(roomTypeDto.getDoubleRent());
		entity.setRoomTypeDescription(roomTypeDto.getRoomTypeDescription());
		entity.setChannelId(roomTypeDto.getChannelId());
		entity.setTenant(tenant);
		
		Optional<RoomTypeMaster> existing =
				roomTypeMasterRepo.findByRoomTypeIgnoreCaseAndTenant_Id(roomTypeDto.getRoomType(), tenantId);
	
	
	if(existing.isPresent()) {
		throw new CustomException(" Room Type Master Already Exists with this Tenant Id ", HttpStatus.BAD_REQUEST);
	}
	
	RoomTypeMaster saved = roomTypeMasterRepo.save(entity);
	return mapToDTO(saved);

}
	
	
	
	
	public List<RoomTypeDTO> geAllRoomTypeByTenant(Long tenantId){
		
		if(!tenantRepository.existsById(tenantId)) {
			throw new CustomException(" Tenant Not Found ", HttpStatus.NOT_FOUND);
		}
		
		List<RoomTypeMaster> roomTypeMaster = roomTypeMasterRepo.findByTenant_Id(tenantId);
		return roomTypeMaster.stream()
				.map(this::mapToDTO)
				.toList();
		
		
		
	}
}