package com.Hotel_Platform.Hotel_Platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.dto.TableMasterDTO;
import com.Hotel_Platform.Hotel_Platform.service.TableMasterService;

import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.TABLE_MASTER;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(TABLE_MASTER)
public class TableMasterController {

    @Autowired
    private TableMasterService tableMasterService;

    @PostMapping
    public ResponseEntity<SuccessResponse<TableMasterDTO>> createTable(
            @RequestBody TableMasterDTO dto,
            @RequestParam Long tenantId) {

        TableMasterDTO saved = tableMasterService.createTable(dto, tenantId);

        return ResponseEntity.ok(
                new SuccessResponse<>(HttpStatus.OK.value(),
                        "Table Created Successfully", saved));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<TableMasterDTO>>> getAllTables(
            @RequestParam Long tenantId) {

        List<TableMasterDTO> tables = tableMasterService.getAllTablesByTenant(tenantId);

        return ResponseEntity.ok(
                new SuccessResponse<>(HttpStatus.OK.value(),
                        "Tables fetched Successfully", tables));
    }
}
