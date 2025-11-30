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

import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.entity.ItemGroupMaster;
import com.Hotel_Platform.Hotel_Platform.service.ItemGroupMasterService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Hotel/itemgroup")
public class ItemGroupMasterController {
	
	
	@Autowired
	private ItemGroupMasterService itemGroupService;
	
	@PostMapping
	public ResponseEntity<SuccessResponse<ItemGroupMaster>> createGroup(@RequestBody ItemGroupMaster itemGroup,
			                                                            @RequestParam Long tenantId){
		ItemGroupMaster savedGroup = itemGroupService.createGroup(itemGroup, tenantId);
		
		SuccessResponse<ItemGroupMaster> response = new SuccessResponse<>(
				HttpStatus.OK.value(), 
				"Item Group Created successfully", savedGroup);
		
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<ItemGroupMaster>>> getAllGroups(@RequestParam Long tenantId){
		List<ItemGroupMaster> listGroups = itemGroupService.getAllGroupsByTenant(tenantId);
		
		SuccessResponse<List<ItemGroupMaster>> response = new SuccessResponse<>(HttpStatus.OK.value(),
				"Item Group Fetched Successfully", listGroups);
		
		return ResponseEntity.ok(response);
	}

}
