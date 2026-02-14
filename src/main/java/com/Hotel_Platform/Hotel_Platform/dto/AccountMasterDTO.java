package com.Hotel_Platform.Hotel_Platform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountMasterDTO {

    private Long id;
    private String partyName;
    private String address1;
    private String address2;
    private String pinCode;
    private String gstNo;
    private String mobileNo;
    private String email;
    private String accountType;   // Enum as String
    private Boolean creditAllow;
    private TenantSummaryDTO tenant;
}

