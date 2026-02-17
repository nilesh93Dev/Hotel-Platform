package com.Hotel_Platform.Hotel_Platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_master")
public class CustomerMaster {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "moble_no", nullable = false)
	private String mobileNo;
	
	@Column(name = "cust_name", nullable = false)
	private String custName;
	
	@Column(name = "address1")
	private String address1;
	
	@Column(name = "address2")
	private String address2;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "gst_no")
	private String gstNo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "reference_name")
	private String referenceName;
	
	@Column(name = "reference_mobile_no")
	private String referenceMobileNo;
	
	@Column(name = "remarks")
	private String remarks;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenants_id")
	@JsonIgnore
	private Tenant tenant;
	
	
	
	
	
	
	
	
	
	
	
	

}
