package com.ssafy.homesns.dto;

import java.util.List;

public class HistoryTravelDto {

	private int result; 
	
	private int feedId; 
	private float lat;
	private float lng; 
	
	private List<MainFileDto> fileDto;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public List<MainFileDto> getFileDto() {
		return fileDto;
	}

	public void setFileDto(List<MainFileDto> fileDto) {
		this.fileDto = fileDto;
	}

	@Override
	public String toString() {
		return "LocationResultDto [result=" + result + ", feedId=" + feedId + ", lat=" + lat + ", lng=" + lng
				+ ", fileDto=" + fileDto + "]";
	}
	
	
	
	
}
