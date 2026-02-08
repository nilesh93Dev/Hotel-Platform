package com.Hotel_Platform.Hotel_Platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.FloorMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TableMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.entity.FloorMaster;
import com.Hotel_Platform.Hotel_Platform.entity.TableMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.enums.TableStatus;
import com.Hotel_Platform.Hotel_Platform.repository.FloorMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TableMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableMasterService {

    @Autowired
    private TableMasterRepository tableMasterRepo;

    @Autowired
    private FloorMasterRepository floorMasterRepo;

    @Autowired
    private TenantRepository tenantRepository;

    private TableMasterDTO mapToDTO(TableMaster table) {
        TableMasterDTO dto = new TableMasterDTO();
        dto.setId(table.getId());
        dto.setTableName(table.getTableName());
        dto.setCurrentStatus(table.getCurrentStatus().name());

        FloorMaster floor = table.getFloor();
        if (floor != null) {
            FloorMasterDTO floorDto = new FloorMasterDTO();
            floorDto.setId(floor.getId());
            floorDto.setFloorName(floor.getFloorName());

            TenantSummaryDTO tenantDto = new TenantSummaryDTO();
            tenantDto.setId(floor.getTenant().getId());
            tenantDto.setName(floor.getTenant().getName());
            floorDto.setTenant(tenantDto);

            dto.setFloor(floorDto);
        }

        Tenant tenant = table.getTenant();
        if (tenant != null) {
            TenantSummaryDTO tenantDto = new TenantSummaryDTO();
            tenantDto.setId(tenant.getId());
            tenantDto.setName(tenant.getName());
            dto.setTenant(tenantDto);
        }

        return dto;
    }

    public TableMasterDTO createTable(TableMasterDTO dto, Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND));

        FloorMaster floor = floorMasterRepo.findById(dto.getFloor().getId())
                .orElseThrow(() -> new CustomException("Floor Not Found", HttpStatus.NOT_FOUND));

        Optional<TableMaster> existing =
                tableMasterRepo.findByTableNameIgnoreCaseAndTenant_Id(dto.getTableName(), tenantId);

        if (existing.isPresent()) {
            throw new CustomException("Table Already Exists with this Tenant Id", HttpStatus.BAD_REQUEST);
        }

        TableMaster entity = new TableMaster();
        entity.setTableName(dto.getTableName());
        entity.setFloor(floor);
        entity.setTenant(tenant);
        entity.setCurrentStatus(TableStatus.valueOf(dto.getCurrentStatus().toUpperCase()));

        TableMaster saved = tableMasterRepo.save(entity);
        return mapToDTO(saved);
    }

    public List<TableMasterDTO> getAllTablesByTenant(Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND));

        List<TableMaster> tables = tableMasterRepo.findByTenant_Id(tenantId);

        return tables.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}


