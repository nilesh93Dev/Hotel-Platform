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

import com.Hotel_Platform.Hotel_Platform.dto.RoomMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.service.RoomMasterService;

import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.ROOM_MASTER;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(ROOM_MASTER)
public class RoomMasterController {
	
	@Autowired
	private RoomMasterService roomMasterSer;
	
	@PostMapping
	public ResponseEntity<SuccessResponse<RoomMasterDTO>> createRoom(
			@RequestBody RoomMasterDTO roomMasterDto,
			@RequestParam Long tenantId) {
		
		RoomMasterDTO saved = roomMasterSer.createRoom(roomMasterDto, tenantId);
		
		return ResponseEntity.ok(
				new SuccessResponse<>(HttpStatus.OK.value(), " Room  Master Created Successfully ",  saved));
	}
	
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<RoomMasterDTO>>> getAllRooms(
			@RequestParam Long tenantId){
		
		List<RoomMasterDTO> roomList = roomMasterSer.getAllRoomsByTenant(tenantId);
		
		return ResponseEntity.ok(
				new SuccessResponse<>(HttpStatus.OK.value(), " Room Master fetched Successfully ", roomList));
	}
	

}
