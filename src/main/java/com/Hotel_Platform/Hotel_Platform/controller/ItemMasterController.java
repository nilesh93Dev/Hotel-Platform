package com.Hotel_Platform.Hotel_Platform.controller;
import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.ITEM_MASTER;

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

import com.Hotel_Platform.Hotel_Platform.dto.ItemMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.service.ItemMasterService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(ITEM_MASTER)

public class ItemMasterController {

    @Autowired
    private ItemMasterService itemMasterService;

    // Create Item
    @PostMapping
    public ResponseEntity<SuccessResponse<ItemMasterDTO>> createItem(
            @RequestBody ItemMasterDTO itemDto,
            @RequestParam Long tenantId) {

        ItemMasterDTO saved = itemMasterService.createItem(itemDto, tenantId);

        return ResponseEntity.ok(
                new SuccessResponse<>(HttpStatus.OK.value(), "Item Master Created Successfully", saved));
    }

    // Get All Items by Tenant
    @GetMapping
    public ResponseEntity<SuccessResponse<List<ItemMasterDTO>>> getAllItems(
            @RequestParam Long tenantId) {

        List<ItemMasterDTO> items = itemMasterService.getAllItemsByTenant(tenantId);

        return ResponseEntity.ok(
                new SuccessResponse<>(HttpStatus.OK.value(), "Item Master fetched Successfully", items));
    }
}
