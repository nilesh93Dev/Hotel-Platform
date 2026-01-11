package com.Hotel_Platform.Hotel_Platform.dto;

public class FloorMasterDTO {
	
	private Long id;
	private String floorName;
	private TenantSummaryDTO tenant;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public TenantSummaryDTO getTenant() {
		return tenant;
	}
	public void setTenant(TenantSummaryDTO tenant) {
		this.tenant = tenant;
	}
	
	

}
