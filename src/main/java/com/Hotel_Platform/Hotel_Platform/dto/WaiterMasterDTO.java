package com.Hotel_Platform.Hotel_Platform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WaiterMasterDTO {
	
	private Long id;
	private String waiterName;
	//private String email;
	private RoleDTO role;
	private TenantSummaryDTO tenant;
	
	

}
