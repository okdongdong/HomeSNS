package com.ssafy.homesns.dto;

import java.util.List;

public class FeedResultDto {
	private int result;
	private FeedDto feedDto;
	private List<FeedDto> feedList;
	private int feedId;
	
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


	
	
}
