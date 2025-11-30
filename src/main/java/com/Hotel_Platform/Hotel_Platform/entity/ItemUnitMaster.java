package com.Hotel_Platform.Hotel_Platform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Table(
    name = "item_unit_master",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"unit_name", "tenant_id"})
    }
)
public class ItemUnitMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_name", nullable = false)
    private String unitName;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    // 👇 Explicit default constructor
    public ItemUnitMaster() {
    }

    public ItemUnitMaster(Long id, String unitName, Tenant tenant) {
        this.id = id;
        this.unitName = unitName;
        this.tenant = tenant;
    }

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getUnitName() {
//		return unitName;
//	}
//
//	public void setUnitName(String unitName) {
//		this.unitName = unitName;
//	}
//
//	public Tenant getTenant() {
//		return tenant;
//	}
//
//	public void setTenant(Tenant tenant) {
//		this.tenant = tenant;
//	}
	
	
	

}
