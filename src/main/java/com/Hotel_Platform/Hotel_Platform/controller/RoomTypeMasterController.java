package com.Hotel_Platform.Hotel_Platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Platform.Hotel_Platform.dto.RoomTypeDTO;
import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.service.RoomTypeMasterService;

import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.ROOM_TYPE_MASTER;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(ROOM_TYPE_MASTER)
public class RoomTypeMasterController {
	
	@Autowired
	private RoomTypeMasterService roomTypeMasterServ;
	
	
	@PostMapping
	public ResponseEntity<SuccessResponse<RoomTypeDTO>> createRoomType(
			@RequestBody RoomTypeDTO roomTypeDto,
			@RequestParam Long tenantId) {
		
		RoomTypeDTO saved = roomTypeMasterServ.createRoomType(roomTypeDto, tenantId);
		
		return ResponseEntity.ok(
				new SuccessResponse<>(HttpStatus.OK.value(), " Room Type Master Created Successfully ", saved));
	}
	
	
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<RoomTypeDTO>>> getAllRoomType(
			@RequestParam Long tenantId) {
		
		List<RoomTypeDTO> listRoomTypeDto = roomTypeMasterServ.geAllRoomTypeByTenant(tenantId);
		
		SuccessResponse<List<RoomTypeDTO>> response = new SuccessResponse<>(
				HttpStatus.OK.value(), " Room Type fetched Successfully ", listRoomTypeDto);
		return ResponseEntity.ok(response);
	}

}
