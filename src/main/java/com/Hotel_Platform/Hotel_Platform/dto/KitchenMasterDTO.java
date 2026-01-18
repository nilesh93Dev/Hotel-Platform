package com.Hotel_Platform.Hotel_Platform.dto;

public class KitchenMasterDTO {
	
	private Long id;
	private String kitchenName;
	private String printerName;
	private TenantSummaryDTO tenant;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKitchenName() {
		return kitchenName;
	}
	public void setKitchenName(String kitchenName) {
		this.kitchenName = kitchenName;
	}
	public String getPrinterName() {
		return printerName;
	}
	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}
	public TenantSummaryDTO getTenant() {
		return tenant;
	}
	public void setTenant(TenantSummaryDTO tenant) {
		this.tenant = tenant;
	}
	
	

}
