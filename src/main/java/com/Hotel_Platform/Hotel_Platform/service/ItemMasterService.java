package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.DishHeadDTO;
import com.Hotel_Platform.Hotel_Platform.dto.ItemGroupDTO;
import com.Hotel_Platform.Hotel_Platform.dto.ItemMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.ItemUnitDTO;
import com.Hotel_Platform.Hotel_Platform.dto.KitchenMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.entity.DishHeadMaster;
import com.Hotel_Platform.Hotel_Platform.entity.ItemGroupMaster;
import com.Hotel_Platform.Hotel_Platform.entity.ItemMaster;
import com.Hotel_Platform.Hotel_Platform.entity.ItemUnitMaster;
import com.Hotel_Platform.Hotel_Platform.entity.KitchenMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.DishHeadMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.ItemGroupMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.ItemMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.ItemUnitMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.KitchenMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class ItemMasterService {

    @Autowired
    private ItemMasterRepository itemMasterRepo;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private KitchenMasterRepository kitchenRepo;

    @Autowired
    private ItemUnitMasterRepository itemUnitRepo;

    @Autowired
    private ItemGroupMasterRepository itemGroupRepo;

    @Autowired
    private DishHeadMasterRepository dishHeadRepo;

    // 🔹 DTO Mapper
    private ItemMasterDTO mapToDTO(ItemMaster item) {
        ItemMasterDTO dto = new ItemMasterDTO();
        dto.setId(item.getId());
        dto.setItemName(item.getItemName());
        dto.setBarcode(item.getBarcode());
        dto.setHsnCode(item.getHsnCode());
        dto.setItemDescription(item.getItemDescription());
        dto.setDineInRate(item.getDineInRate());
        dto.setTakeawayRate(item.getTakeawayRate());
        dto.setDeliveryRate(item.getDeliveryRate());
        dto.setOnlineRate(item.getOnlineRate());
        dto.setMrp(item.getMrp());
        dto.setPurchaseRate(item.getPurchaseRate());
        dto.setGstPercent(item.getGstPercent());
        dto.setCessPercent(item.getCessPercent());
        dto.setDiscountPercent(item.getDiscountPercent());
        dto.setOpeningStock(item.getOpeningStock());
        dto.setIsFavourite(item.getIsFavourite());
        dto.setInPackage(item.getInPackage());
        dto.setIsDiscountable(item.getIsDiscountable());
        dto.setKitchenStock(item.getKitchenStock());
        dto.setIsVeg(item.getIsVeg());
        dto.setFileName(item.getFileName());

        if (item.getTenant() != null) {
            TenantSummaryDTO tenantDto = new TenantSummaryDTO();
            tenantDto.setId(item.getTenant().getId());
            tenantDto.setName(item.getTenant().getName());
            dto.setTenant(tenantDto);
        }

        if (item.getKitchen() != null) {
            KitchenMasterDTO kitchenDto = new KitchenMasterDTO();
            kitchenDto.setId(item.getKitchen().getId());
            kitchenDto.setKitchenName(item.getKitchen().getKitchenName());
            kitchenDto.setPrinterName(item.getKitchen().getPrinterName());

            TenantSummaryDTO tenantDto = new TenantSummaryDTO();
            tenantDto.setId(item.getKitchen().getTenant().getId());
            tenantDto.setName(item.getKitchen().getTenant().getName());
            kitchenDto.setTenant(tenantDto);

            dto.setKitchen(kitchenDto);
        }

        if (item.getItemUnit() != null) {
            ItemUnitDTO unitDto = new ItemUnitDTO();
            unitDto.setId(item.getItemUnit().getId());
            unitDto.setUnitName(item.getItemUnit().getUnitName());

            TenantSummaryDTO tenantDto = new TenantSummaryDTO();
            tenantDto.setId(item.getItemUnit().getTenant().getId());
            tenantDto.setName(item.getItemUnit().getTenant().getName());
            unitDto.setTenant(tenantDto);

            dto.setItemUnit(unitDto);
        }

        if (item.getItemGroupMasters() != null) {
            ItemGroupDTO groupDto = new ItemGroupDTO();
            groupDto.setId(item.getItemGroupMasters().getId());
            groupDto.setGroupName(item.getItemGroupMasters().getGroupName());

            TenantSummaryDTO tenantDto = new TenantSummaryDTO();
            tenantDto.setId(item.getItemGroupMasters().getTenant().getId());
            tenantDto.setName(item.getItemGroupMasters().getTenant().getName());
            groupDto.setTenant(tenantDto);

            dto.setItemGroup(groupDto);
        }

        if (item.getDishHeadMaster() != null) {
            DishHeadDTO dishDto = new DishHeadDTO();
            dishDto.setId(item.getDishHeadMaster().getId());
            dishDto.setDishHeadName(item.getDishHeadMaster().getDishHeadName());

            TenantSummaryDTO tenantDto = new TenantSummaryDTO();
            tenantDto.setId(item.getDishHeadMaster().getTenant().getId());
            tenantDto.setName(item.getDishHeadMaster().getTenant().getName());
            dishDto.setTenant(tenantDto);

            if (item.getDishHeadMaster().getDishType() != null) {
                dishDto.setDishTypeId(item.getDishHeadMaster().getDishType().getId());
            }

            dto.setDishHead(dishDto);
        }

        return dto;
    }

    // 🔹 Create Item
    public ItemMasterDTO createItem(ItemMasterDTO dto, Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND));

        KitchenMaster kitchen = kitchenRepo.findById(dto.getKitchen().getId())
                .orElseThrow(() -> new CustomException("Kitchen Not Found", HttpStatus.NOT_FOUND));

        ItemUnitMaster unit = itemUnitRepo.findById(dto.getItemUnit().getId())
                .orElseThrow(() -> new CustomException("Item Unit Not Found", HttpStatus.NOT_FOUND));

        ItemGroupMaster group = itemGroupRepo.findById(dto.getItemGroup().getId())
                .orElseThrow(() -> new CustomException("Item Group Not Found", HttpStatus.NOT_FOUND));

        DishHeadMaster dishHead = dishHeadRepo.findById(dto.getDishHead().getId())
                .orElseThrow(() -> new CustomException("Dish Head Not Found", HttpStatus.NOT_FOUND));

        Optional<ItemMaster> existing = itemMasterRepo.findByItemNameIgnoreCaseAndTenant_Id(dto.getItemName(), tenantId);
        if (existing.isPresent()) {
            throw new CustomException("Item Master Already Exists with this Tenant Id", HttpStatus.BAD_REQUEST);
        }

        ItemMaster entity = new ItemMaster();
        entity.setItemName(dto.getItemName());
        entity.setBarcode(dto.getBarcode());
        entity.setHsnCode(dto.getHsnCode());
        entity.setTenant(tenant);
        entity.setKitchen(kitchen);
        entity.setItemUnit(unit);
        entity.setItemGroupMasters(group);
        entity.setDishHeadMaster(dishHead);
        entity.setDineInRate(dto.getDineInRate());
        entity.setTakeawayRate(dto.getTakeawayRate());
        entity.setDeliveryRate(dto.getDeliveryRate());
        entity.setOnlineRate(dto.getOnlineRate());
        entity.setMrp(dto.getMrp());
        entity.setPurchaseRate(dto.getPurchaseRate());
        entity.setGstPercent(dto.getGstPercent());
        entity.setCessPercent(dto.getCessPercent());
        entity.setDiscountPercent(dto.getDiscountPercent());
        entity.setOpeningStock(dto.getOpeningStock());
        entity.setIsFavourite(dto.getIsFavourite());
        entity.setInPackage(dto.getInPackage());
        entity.setIsDiscountable(dto.getIsDiscountable());
        entity.setKitchenStock(dto.getKitchenStock());
        entity.setIsVeg(dto.getIsVeg());
        entity.setFileName(dto.getFileName());
        entity.setItemDescription(dto.getItemDescription());

        ItemMaster saved = itemMasterRepo.save(entity);
        return mapToDTO(saved);
    }

    // 🔹 Get All Items by Tenant
    public List<ItemMasterDTO> getAllItemsByTenant(Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND));

        List<ItemMaster> items = itemMasterRepo.findByTenant_Id(tenantId);
        return items.stream().map(this::mapToDTO).toList();
    }
}
