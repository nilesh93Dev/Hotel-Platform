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

import com.Hotel_Platform.Hotel_Platform.dto.DishTypeDTO;
import com.Hotel_Platform.Hotel_Platform.dto.ItemGroupDTO;
import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.service.DishTypeMasterService;

import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.DISH_TYPE_MASTER;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(DISH_TYPE_MASTER)
public class DishTypeMasterController {
	
	@Autowired
	private DishTypeMasterService dishTypeService;
	
	@PostMapping
	public ResponseEntity<SuccessResponse<DishTypeDTO>> createDishType(
	        @RequestBody DishTypeDTO dishTypeDto,
	        @RequestParam Long tenantId) {

		DishTypeDTO dto = dishTypeService.createDishType(dishTypeDto, tenantId);

	    return ResponseEntity.ok(
	        new SuccessResponse<>(200, "Dish Type Created successfully", dto)
	    );
	}
	
	
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<DishTypeDTO>>> getAllDishTypes(
			@RequestParam Long tenantId) {
		
		List<DishTypeDTO> listDishType = dishTypeService.getAllDishTypesByTenant(tenantId);
		SuccessResponse<List<DishTypeDTO>> response = new SuccessResponse<>(
				HttpStatus.OK.value(),
				" Dish Type fetched Successfully ", listDishType);
		return ResponseEntity.ok(response);
	}

}
