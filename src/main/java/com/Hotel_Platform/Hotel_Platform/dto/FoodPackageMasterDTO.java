package com.Hotel_Platform.Hotel_Platform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodPackageMasterDTO {
	
	private Long id;
	
	private String FoodName;
	
	private Double rate;
	
	private Double gstPercentage;
	
	private String gstType;
	
	private TenantSummaryDTO tenant;

}
