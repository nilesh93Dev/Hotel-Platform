package com.Hotel_Platform.Hotel_Platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_master")
public class ItemMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String itemName;
	
	private String barcode;
	
	private String hsnCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name =  " tenants_id")
	@JsonIgnore
	private Tenant tenant;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kitchens_id")
	@JsonIgnore
	private KitchenMaster kitchen;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itemUnit_id")
	private ItemUnitMaster itemUnit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itemGroups_id")
	@JsonIgnore
	private ItemGroupMaster itemGroupMasters;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dishHeads_id")
	@JsonIgnore
	private DishHeadMaster dishHeadMaster;
	
	
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
	
	@Column(length = 1000) 
	private String itemDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public KitchenMaster getKitchen() {
		return kitchen;
	}

	public void setKitchen(KitchenMaster kitchen) {
		this.kitchen = kitchen;
	}

	public ItemUnitMaster getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(ItemUnitMaster itemUnit) {
		this.itemUnit = itemUnit;
	}

	public ItemGroupMaster getItemGroupMasters() {
		return itemGroupMasters;
	}

	public void setItemGroupMasters(ItemGroupMaster itemGroupMasters) {
		this.itemGroupMasters = itemGroupMasters;
	}

	public DishHeadMaster getDishHeadMaster() {
		return dishHeadMaster;
	}

	public void setDishHeadMaster(DishHeadMaster dishHeadMaster) {
		this.dishHeadMaster = dishHeadMaster;
	}

	public Double getDineInRate() {
		return dineInRate;
	}

	public void setDineInRate(Double dineInRate) {
		this.dineInRate = dineInRate;
	}

	public Double getTakeawayRate() {
		return takeawayRate;
	}

	public void setTakeawayRate(Double takeawayRate) {
		this.takeawayRate = takeawayRate;
	}

	public Double getDeliveryRate() {
		return deliveryRate;
	}

	public void setDeliveryRate(Double deliveryRate) {
		this.deliveryRate = deliveryRate;
	}

	public Double getOnlineRate() {
		return onlineRate;
	}

	public void setOnlineRate(Double onlineRate) {
		this.onlineRate = onlineRate;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public Double getPurchaseRate() {
		return purchaseRate;
	}

	public void setPurchaseRate(Double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	public Double getGstPercent() {
		return gstPercent;
	}

	public void setGstPercent(Double gstPercent) {
		this.gstPercent = gstPercent;
	}

	public Double getCessPercent() {
		return cessPercent;
	}

	public void setCessPercent(Double cessPercent) {
		this.cessPercent = cessPercent;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Double getOpeningStock() {
		return openingStock;
	}

	public void setOpeningStock(Double openingStock) {
		this.openingStock = openingStock;
	}

	public Boolean getIsFavourite() {
		return isFavourite;
	}

	public void setIsFavourite(Boolean isFavourite) {
		this.isFavourite = isFavourite;
	}

	public Boolean getInPackage() {
		return inPackage;
	}

	public void setInPackage(Boolean inPackage) {
		this.inPackage = inPackage;
	}

	public Boolean getIsDiscountable() {
		return isDiscountable;
	}

	public void setIsDiscountable(Boolean isDiscountable) {
		this.isDiscountable = isDiscountable;
	}

	public Boolean getKitchenStock() {
		return kitchenStock;
	}

	public void setKitchenStock(Boolean kitchenStock) {
		this.kitchenStock = kitchenStock;
	}

	public Boolean getIsVeg() {
		return isVeg;
	}

	public void setIsVeg(Boolean isVeg) {
		this.isVeg = isVeg;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	
	
	
	

}
