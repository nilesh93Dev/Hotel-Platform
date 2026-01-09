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

import com.Hotel_Platform.Hotel_Platform.dto.DishHeadDTO;
import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.service.DishHeadMasterService;

import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.DISH_HEAD_MASTER;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(DISH_HEAD_MASTER)
public class DishHeadMasterController {
	
	@Autowired
    private DishHeadMasterService dishHeadService;

    @PostMapping
    public ResponseEntity<SuccessResponse<DishHeadDTO>> createDishHead(
            @RequestBody DishHeadDTO dto,
            @RequestParam Long tenantId) {

        DishHeadDTO saved = dishHeadService.createDishHead(dto, tenantId);
        return ResponseEntity.ok(
                new SuccessResponse<>(HttpStatus.OK.value(), "Dish Head Created Successfully", saved)
        );
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<DishHeadDTO>>> getAllDishHeads(@RequestParam Long tenantId) {
        List<DishHeadDTO> list = dishHeadService.getAllDishHeadsByTenant(tenantId);
        return ResponseEntity.ok(
                new SuccessResponse<>(HttpStatus.OK.value(), "Dish Heads Fetched Successfully", list)
        );
    }
}
