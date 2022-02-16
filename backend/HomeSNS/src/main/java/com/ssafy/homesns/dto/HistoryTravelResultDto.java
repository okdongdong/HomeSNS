package com.ssafy.homesns.dto;

import java.util.List;

public class HistoryTravelResultDto {

	private int result; 
	private List<HistoryTravelDto> groupFeedList;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<HistoryTravelDto> getGroupFeedList() {
		return groupFeedList;
	}
	public void setGroupFeedList(List<HistoryTravelDto> groupFeedList) {
		this.groupFeedList = groupFeedList;
	}
	@Override
	public String toString() {
		return "HistoryTravelResultDto [result=" + result + ", groupFeedList=" + groupFeedList + "]";
	}
	
	
	
}
