package com.Hotel_Platform.Hotel_Platform.entity;

import com.Hotel_Platform.Hotel_Platform.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_master")
public class AccountMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String partyName;
	
	private String address1;
	
	private String address2;
	
	private String pinCode;
	
	private String gstNo;
	
	private String mobileNo;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenants_id")
	@JsonIgnore
	private Tenant tenant;

}












