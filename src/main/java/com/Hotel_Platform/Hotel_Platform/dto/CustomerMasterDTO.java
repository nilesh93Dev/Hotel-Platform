package com.Hotel_Platform.Hotel_Platform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerMasterDTO {

    private Long id;
    private String mobileNo;
    private String custName;
    private String address1;
    private String address2;
    private String companyName;
    private String gstNo;
    private String email;
    private String referenceName;
    private String referenceMobileNo;
    private String remarks;

    private TenantSummaryDTO tenant;  // Tenant info summary
}


