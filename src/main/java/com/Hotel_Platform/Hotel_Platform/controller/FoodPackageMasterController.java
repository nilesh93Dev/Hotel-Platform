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

import com.Hotel_Platform.Hotel_Platform.dto.FoodPackageMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.service.FoodPackageMasterService;

import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.FOOD_PACKAGE_MASTER;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(FOOD_PACKAGE_MASTER)
public class FoodPackageMasterController {
	
	@Autowired
	private FoodPackageMasterService foodPackageService;
	
	@PostMapping
	public ResponseEntity<SuccessResponse<FoodPackageMasterDTO>> createFoodPackage(
			@RequestBody FoodPackageMasterDTO foodPackageMasterDto,
			@RequestParam Long tenantId) {
		
		FoodPackageMasterDTO saved = foodPackageService.createFoodPackage(foodPackageMasterDto, tenantId);
		
		return ResponseEntity.ok(
				new SuccessResponse<>(HttpStatus.OK.value(), " Food Package Created Successfully ", saved));
	}
	
	
	
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<FoodPackageMasterDTO>>> getAllFoodPackages(
			@RequestParam Long tenantId) {
		
		List<FoodPackageMasterDTO> packageList = foodPackageService.getAllFoodPackagesByTenant(tenantId);
		
		return ResponseEntity.ok(
				new SuccessResponse<>(HttpStatus.OK.value(), " Food Packages fetched Successfully ", packageList));
	}
	

}
