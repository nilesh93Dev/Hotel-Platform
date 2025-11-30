package com.Hotel_Platform.Hotel_Platform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

	@Entity
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public class Role {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String name;
		
//		@ManyToOne
//		@JoinColumn(name = "tenant_id")
//		private Tenant tenant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Tenant getTenant() {
//		return tenant;
//	}
//
//	public void setTenant(Tenant tenant) {
//		this.tenant = tenant;
//	}

	
	

}
