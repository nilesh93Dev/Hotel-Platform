package com.Hotel_Platform.Hotel_Platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.entity.ItemUnitMaster;
import com.Hotel_Platform.Hotel_Platform.service.ItemUnitMasterService;
import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.UNIT_MASTER;


@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("Hotel/itemunits")
@RequestMapping(UNIT_MASTER)
public class ItemUnitMasterController {
	
	
	@Autowired
	private ItemUnitMasterService itemunitService;
	
	@PostMapping
    public ResponseEntity<SuccessResponse<ItemUnitMaster>> createUnit(
            @RequestBody ItemUnitMaster itemUnit,
            @RequestParam Long tenantId) {

        ItemUnitMaster savedUnit = itemunitService.createUnit(itemUnit, tenantId);

        SuccessResponse<ItemUnitMaster> response = new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Unit created Successfully",
                savedUnit);

        return ResponseEntity.ok(response);
    }

		
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<ItemUnitMaster>>> getAllUnits(@RequestParam Long tenantId){
		
		List<ItemUnitMaster> units = itemunitService.getAllUnitsByTenant(tenantId);
		
		SuccessResponse<List<ItemUnitMaster>> response = new SuccessResponse<>(
				HttpStatus.OK.value(),
				"Unit Fetch Successfully",
				units);
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<ItemUnitMaster>> updateUnit(
			               @PathVariable Long id,
			               @RequestParam Long tenantId,
			               @RequestBody ItemUnitMaster updatedUnit
			               ) {
		ItemUnitMaster unit = itemunitService.updateUnit(id, tenantId, updatedUnit);
		
		SuccessResponse<ItemUnitMaster> rsponse = new SuccessResponse<>(
		          HttpStatus.OK.value(),
		          "Unit Updated Successfully!",
		          unit);
		
		return ResponseEntity.ok(rsponse);
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUnit(
			@PathVariable Long id,
			@RequestParam Long tenantId) {
		
		itemunitService.deleteUnit(id, tenantId);
		return ResponseEntity.ok(" Item Unit delete Successfully! ");
	}
	

}