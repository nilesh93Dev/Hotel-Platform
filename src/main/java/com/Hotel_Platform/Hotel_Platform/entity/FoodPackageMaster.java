package com.Hotel_Platform.Hotel_Platform.entity;

import com.Hotel_Platform.Hotel_Platform.enums.GstType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food_package_master")
public class FoodPackageMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String foodName;
	private Double rate;
	
	@Column(name = "gst_percentage")
	private Double gstPercentage;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gst_type")
	private GstType gstType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenants_id")
	@JsonIgnore
	private Tenant tenant;
	
	
	
	
	
	
	
	
	
	
	
	
	

}
