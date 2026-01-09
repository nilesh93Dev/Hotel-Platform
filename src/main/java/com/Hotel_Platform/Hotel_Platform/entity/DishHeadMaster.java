package com.Hotel_Platform.Hotel_Platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dish_head_master")
public class DishHeadMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dishHeadName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_type_id")
    @JsonIgnore
    private DishTypeMaster dishType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenants_id")
    @JsonIgnore
    private Tenant tenant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDishHeadName() {
		return dishHeadName;
	}

	public void setDishHeadName(String dishHeadName) {
		this.dishHeadName = dishHeadName;
	}

	public DishTypeMaster getDishType() {
		return dishType;
	}

	public void setDishType(DishTypeMaster dishType) {
		this.dishType = dishType;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
    
    
    
    
}