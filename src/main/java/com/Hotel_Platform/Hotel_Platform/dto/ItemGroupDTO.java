package com.Hotel_Platform.Hotel_Platform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemGroupDTO {
	
	private Long id;
	private String groupName;
	private TenantSummaryDTO tenant;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public TenantSummaryDTO getTenant() {
		return tenant;
	}
	public void setTenant(TenantSummaryDTO tenant) {
		this.tenant = tenant;
	}
	
	

}