package com.Hotel_Platform.Hotel_Platform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishHeadDTO {
    private Long id;
    private String dishHeadName;
    private Long dishTypeId;
    private TenantSummaryDTO tenant;

    // Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getDishHeadName() { return dishHeadName; }
//    public void setDishHeadName(String dishHeadName) { this.dishHeadName = dishHeadName; }
//
//    public Long getDishTypeId() { return dishTypeId; }
//    public void setDishTypeId(Long dishTypeId) { this.dishTypeId = dishTypeId; }
//
//    public TenantSummaryDTO getTenant() { return tenant; }
//    public void setTenant(TenantSummaryDTO tenant) { this.tenant = tenant; }
}
