package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.FloorMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.RoomMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.RoomTypeDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.entity.FloorMaster;
import com.Hotel_Platform.Hotel_Platform.entity.RoomMaster;
import com.Hotel_Platform.Hotel_Platform.entity.RoomStatus;
import com.Hotel_Platform.Hotel_Platform.entity.RoomTypeMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.FloorMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.RoomMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.RoomTypeMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class RoomMasterService {
	
	@Autowired
	private RoomMasterRepository roomMasterRepo;
	
	@Autowired
	private RoomTypeMasterRepository roomTypeMasterRepo;
	
	@Autowired
    private FloorMasterRepository floorMasterRepo;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	private RoomMasterDTO mapToDTO(RoomMaster room) {
		RoomMasterDTO dto = new RoomMasterDTO();
		dto.setId(room.getId());
		dto.setRoomName(room.getRoomName());
		dto.setRentPerDay(room.getRentPerDay());
		dto.setExtraBedCharge(room.getExtraBedCharge());
		dto.setCurrentStatus(room.getCurrentStatus().name());
		
		RoomTypeMaster roomType = room.getRoomType();
		if(roomType != null) {
			
			RoomTypeDTO roomTypeDto = new RoomTypeDTO();
			roomTypeDto.setId(roomType.getId());
			roomTypeDto.setRoomType(roomType.getRoomType());
			roomTypeDto.setTotalRoom(roomType.getTotalRoom());
			roomTypeDto.setOccupancy(roomType.getOccupancy());
			roomTypeDto.setSingleRent(roomType.getSingleRent());
			roomTypeDto.setDoubleRent(roomType.getDoubleRent());
			roomTypeDto.setRoomTypeDescription(roomType.getRoomTypeDescription());
			roomTypeDto.setChannelId(roomType.getChannelId());
			
			TenantSummaryDTO tenantDto = new TenantSummaryDTO();
			tenantDto.setId(roomType.getTenant().getId());
			tenantDto.setName(roomType.getTenant().getName());
			roomTypeDto.setTenant(tenantDto);
			dto.setRoomType(roomTypeDto);	
			
		}
		
		FloorMaster floor = room.getFloor();
		if(floor != null) {
			
			FloorMasterDTO floorDto = new FloorMasterDTO();
			floorDto.setId(floor.getId());
			floorDto.setFloorName(floor.getFloorName());
			
			
			TenantSummaryDTO tenantDto = new TenantSummaryDTO();
			tenantDto.setId(floor.getTenant().getId());
			tenantDto.setName(floor.getTenant().getName());
			floorDto.setTenant(tenantDto);
			dto.setFloor(floorDto);
			
		}
		
		
		Tenant tenant = room.getTenant();
		if (tenant != null) {
		    TenantSummaryDTO tenantDto = new TenantSummaryDTO();
		    tenantDto.setId(tenant.getId());
		    tenantDto.setName(tenant.getName());
		    dto.setTenant(tenantDto);
		}
		return dto;
		}
		
		
		public RoomMasterDTO createRoom(RoomMasterDTO roomDto, Long tenantId) {
	        Tenant tenant = tenantRepository.findById(tenantId)
	                .orElseThrow(() -> new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND));

	        RoomTypeMaster roomType = roomTypeMasterRepo.findById(roomDto.getRoomType().getId())
	                .orElseThrow(() -> new CustomException("Room Type Not Found", HttpStatus.NOT_FOUND));

	        FloorMaster floor = floorMasterRepo.findById(roomDto.getFloor().getId())
	                .orElseThrow(() -> new CustomException("Floor Not Found", HttpStatus.NOT_FOUND));

	        // Duplicate check
	        Optional<RoomMaster> existing =
	                roomMasterRepo.findByRoomNameIgnoreCaseAndTenant_Id(roomDto.getRoomName(), tenantId);

	        if (existing.isPresent()) {
	            throw new CustomException("Room Master Already Exists with this Tenant Id", HttpStatus.BAD_REQUEST);
	        }

	        RoomMaster entity = new RoomMaster();
	        entity.setRoomName(roomDto.getRoomName());
	        entity.setRoomType(roomType);
	        entity.setFloor(floor);
	        entity.setTenant(tenant);
	        entity.setRentPerDay(roomDto.getRentPerDay());
	        entity.setExtraBedCharge(roomDto.getExtraBedCharge());
	        entity.setCurrentStatus(RoomStatus.valueOf(roomDto.getCurrentStatus()));

	        RoomMaster saved = roomMasterRepo.save(entity);
	        return mapToDTO(saved);
	    
	}
		
		
		public List<RoomMasterDTO> getAllRoomsByTenant(Long tenantId) {
			
			Tenant tenant = tenantRepository.findById(tenantId)
					.orElseThrow(() -> new CustomException(" Tenant not Found ", HttpStatus.NOT_FOUND));
			
			List<RoomMaster> viewRooms = roomMasterRepo.findByTenant_Id(tenantId);
			
			return viewRooms.stream()
					.map(this::mapToDTO)
			        .toList();
		}

}
