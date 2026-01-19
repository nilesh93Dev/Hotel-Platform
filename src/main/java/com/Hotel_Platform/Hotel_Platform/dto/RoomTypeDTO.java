package com.Hotel_Platform.Hotel_Platform.dto;

public class RoomTypeDTO {
	
	private Long id;
	private String roomType;
	private int totalRoom;
	private int occupancy;
	private double singleRent;
	private double doubleRent;
	private String roomTypeDescription;
	private String channelId;
	private TenantSummaryDTO tenant;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getTotalRoom() {
		return totalRoom;
	}
	public void setTotalRoom(int totalRoom) {
		this.totalRoom = totalRoom;
	}
	public int getOccupancy() {
		return occupancy;
	}
	public void setOccupancy(int occupancy) {
		this.occupancy = occupancy;
	}
	public double getSingleRent() {
		return singleRent;
	}
	public void setSingleRent(double singleRent) {
		this.singleRent = singleRent;
	}
	public double getDoubleRent() {
		return doubleRent;
	}
	public void setDoubleRent(double doubleRent) {
		this.doubleRent = doubleRent;
	}
	public String getRoomTypeDescription() {
		return roomTypeDescription;
	}
	public void setRoomTypeDescription(String roomTypeDescription) {
		this.roomTypeDescription = roomTypeDescription;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public TenantSummaryDTO getTenant() {
		return tenant;
	}
	public void setTenant(TenantSummaryDTO tenant) {
		this.tenant = tenant;
	}
	
	
	
}