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

import com.Hotel_Platform.Hotel_Platform.dto.KitchenMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.service.KitchenMasterService;

import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.KITCHEN_MASTER;

import java.util.List;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping(KITCHEN_MASTER)
public class KitchenMasterController {
	
	@Autowired
	private KitchenMasterService kitchenMasterSer;
	
	@PostMapping
	public ResponseEntity<SuccessResponse<KitchenMasterDTO>> createKitchen(
			@RequestBody KitchenMasterDTO kitchenDto,
			@RequestParam Long tenantId) {
		
		KitchenMasterDTO saved = kitchenMasterSer.createKitchen(kitchenDto, tenantId);
		return ResponseEntity.ok(
				new SuccessResponse<>(HttpStatus.OK.value(), " Kitchen Master Create Successfully ", saved));
	}
	
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<KitchenMasterDTO>>> getAllKitchen(
			@RequestParam Long tenantId) {
		
		List<KitchenMasterDTO> floorList = kitchenMasterSer.getAllKitchenByTenant(tenantId);
		SuccessResponse<List<KitchenMasterDTO>> response = new SuccessResponse<>(
				HttpStatus.OK.value(),
				" Kitchen Master fetched Successfully ", floorList);
		return ResponseEntity.ok(response);
	}
	
	
	

}
