package com.ssafy.homesns.dto;

public class LocationFavoriteDto {

	private int locationId;
	private int userSeq;
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	@Override
	public String toString() {
		return "LocationFavoriteDto [locationId=" + locationId + ", userSeq=" + userSeq + "]";
	}
	
	
	
}
