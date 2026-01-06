package com.Hotel_Platform.Hotel_Platform.dto;

public class DishTypeDTO {
	
	private Long id;
	private String dishTypeName;
	private TenantSummaryDTO tenant;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDishTypeName() {
		return dishTypeName;
	}
	public void setDishTypeName(String dishTypeName) {
		this.dishTypeName = dishTypeName;
	}
	public TenantSummaryDTO getTenant() {
		return tenant;
	}
	public void setTenant(TenantSummaryDTO tenant) {
		this.tenant = tenant;
	}
	
	
	

}
