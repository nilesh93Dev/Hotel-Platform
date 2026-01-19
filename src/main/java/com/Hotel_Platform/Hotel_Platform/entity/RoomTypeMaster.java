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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room_type_master")
public class RoomTypeMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String roomType;
	
	private int totalRoom;
	
	private int occupancy;
	
	private double singleRent;
	
	private double doubleRent;
	
	private String roomTypeDescription;
	
	private String channelId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenants_id")
	@JsonIgnore
	private Tenant tenant;

}
