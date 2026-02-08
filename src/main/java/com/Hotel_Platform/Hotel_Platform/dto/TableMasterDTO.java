package com.Hotel_Platform.Hotel_Platform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableMasterDTO {
	
	private Long id;
	private String tableName;
	private FloorMasterDTO floor;
	private String currentStatus;
	private TenantSummaryDTO tenant;
	
	
	

}
