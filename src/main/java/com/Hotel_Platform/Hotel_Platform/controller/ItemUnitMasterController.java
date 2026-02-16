package com.Hotel_Platform.Hotel_Platform.controller;

import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.UNIT_MASTER;

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
import com.Hotel_Platform.Hotel_Platform.dto.ItemUnitDTO;
import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.service.ItemUnitMasterService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(UNIT_MASTER)
public class ItemUnitMasterController {
	
	
	@Autowired
	private ItemUnitMasterService itemUnitService;
	
	@PostMapping
	public ResponseEntity<SuccessResponse<ItemUnitDTO>>  createUnit(
			@RequestBody ItemUnitDTO itemUnitDto,
			@RequestParam Long tenantId) {
		
		ItemUnitDTO dto = itemUnitService.createUnit(itemUnitDto, tenantId);
		
		return ResponseEntity.ok(
				new SuccessResponse<>(200, "Item Unit created Successfully", dto));
	}
	
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<ItemUnitDTO>>> getAllUnit(
			@RequestParam Long tenantId) {
		
		List<ItemUnitDTO> unitList = itemUnitService.getAllUnitByTenant(tenantId);
		SuccessResponse<List<ItemUnitDTO>> response = new SuccessResponse<>(
				HttpStatus.OK.value(),
				" Unit Master fetched Successfully", unitList);
		return ResponseEntity.ok(response);
				
	}

}
