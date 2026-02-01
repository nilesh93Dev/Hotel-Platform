package com.Hotel_Platform.Hotel_Platform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemMasterDTO {

    private Long id;
    private String itemName;
    private String barcode;
    private String hsnCode;

    private ItemGroupDTO itemGroup;
    private KitchenMasterDTO kitchen;
    private DishHeadDTO dishHead;
    private ItemUnitDTO itemUnit;

    private Double dineInRate;
    private Double takeawayRate;
    private Double deliveryRate;
    private Double onlineRate;

    private Double mrp;
    private Double purchaseRate;
    private Double gstPercent;
    private Double cessPercent;
    private Double discountPercent;

    private Double openingStock;

    private Boolean isFavourite;
    private Boolean inPackage;
    private Boolean isDiscountable;
    private Boolean kitchenStock;
    private Boolean isVeg;

    private String fileName;
    private String itemDescription;

    private TenantSummaryDTO tenant;

}