package com.ssafy.homesns.dto;

public class EventMemberDto {
	
	private int feedId;
	private int userSeq;
	
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	@Override
	public String toString() {
		return "EventMemberDto [feedId=" + feedId + ", userSeq=" + userSeq + "]";
	}
	
	
}
