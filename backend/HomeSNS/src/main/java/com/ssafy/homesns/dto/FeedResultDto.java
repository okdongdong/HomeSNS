package com.ssafy.homesns.dto;

import java.util.List;

public class FeedResultDto {
	private int result;
	private FeedDto feedDto;
	private List<FeedDto> feedList;
	private int feedId;
	private List<LocationDto> locations;
	private List<GroupMemberDto> members;
	
	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}


	public FeedDto getFeedDto() {
		return feedDto;
	}


	public void setFeedDto(FeedDto feedDto) {
		this.feedDto = feedDto;
	}


	public List<FeedDto> getFeedList() {
		return feedList;
	}


	public void setFeedList(List<FeedDto> feedList) {
		this.feedList = feedList;
	}


	public int getFeedId() {
		return feedId;
	}


	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}


	public List<LocationDto> getLocations() {
		return locations;
	}


	public void setLocations(List<LocationDto> locations) {
		this.locations = locations;
	}


	public List<GroupMemberDto> getMembers() {
		return members;
	}


	public void setMembers(List<GroupMemberDto> members) {
		this.members = members;
	}


	
	
}
