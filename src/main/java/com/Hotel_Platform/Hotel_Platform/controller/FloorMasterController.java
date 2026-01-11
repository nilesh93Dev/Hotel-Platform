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

import com.Hotel_Platform.Hotel_Platform.dto.FloorMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.entity.FloorMaster;
import com.Hotel_Platform.Hotel_Platform.service.FloorMasterService;

import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.FLOOR_MASTER;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(FLOOR_MASTER)
public class FloorMasterController {
	
	@Autowired
	private FloorMasterService floorMasterService;
	
//	@PostMapping
//	public ResponseEntity<SuccessResponse<FloorMaster>> createFloor(
//			@RequestBody FloorMaster floor,
//			@RequestParam Long tenantId) {
//		
//		FloorMaster floors = floorMasterService.createFloor(floor, tenantId);
//		
//		return ResponseEntity.ok(
//				new SuccessResponse<>(HttpStatus.OK.value(), " Floor Master created Successfully", floors));
//	}
	
	
	@PostMapping
	public ResponseEntity<SuccessResponse<FloorMasterDTO>> createFloor(
			@RequestBody FloorMasterDTO floorDto,
			@RequestParam Long tenantId) {
		
		FloorMasterDTO saved = floorMasterService.createFloor(floorDto, tenantId);
		return ResponseEntity.ok(
				new SuccessResponse<>(HttpStatus.OK.value(), " Floor Master Created Successfully ", saved));
		
	}
	
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<FloorMasterDTO>>> getAllFloor(
			@RequestParam Long tenantId) {
		
		List<FloorMasterDTO> floorList = floorMasterService.getAllFloorByTenant(tenantId);
		SuccessResponse<List<FloorMasterDTO>> response = new SuccessResponse<>(
				HttpStatus.OK.value(),
				" Floor Master fetched Successfully", floorList);
		return ResponseEntity.ok(response);
				
	}

}
