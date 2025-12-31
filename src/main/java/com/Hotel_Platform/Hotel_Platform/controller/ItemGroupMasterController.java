package com.Hotel_Platform.Hotel_Platform.controller;

import java.util.List;

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

import com.Hotel_Platform.Hotel_Platform.dto.ItemGroupDTO;
import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.service.ItemGroupMasterService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Hotel/itemgroup")
public class ItemGroupMasterController {
	
	
	@Autowired
	private ItemGroupMasterService itemGroupService;
	
	@PostMapping
	public ResponseEntity<SuccessResponse<ItemGroupDTO>> createGroup(
	        @RequestBody ItemGroupDTO itemGroupDto,
	        @RequestParam Long tenantId) {

	    ItemGroupDTO dto = itemGroupService.createGroup(itemGroupDto, tenantId);
	   
	    System.out.println("Returning DTO tenant class: " + dto.getTenant().getClass().getName());

	    return ResponseEntity.ok(
	        new SuccessResponse<>(200, "Item Group Created successfully", dto)
	    );
	}


	



@GetMapping 
public ResponseEntity<SuccessResponse<List<ItemGroupDTO>>> getAllGroups(
		@RequestParam Long tenantId) { 
	List<ItemGroupDTO> listGroups = itemGroupService.getAllGroupsByTenant(tenantId); 
	SuccessResponse<List<ItemGroupDTO>> response = new SuccessResponse<>( 
			HttpStatus.OK.value(),
			"Item Group Fetched Successfully", listGroups );
	return ResponseEntity.ok(response);
	} }


