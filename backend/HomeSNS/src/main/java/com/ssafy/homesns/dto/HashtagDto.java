package com.ssafy.homesns.dto;

public class HashtagDto {

	private int hashtagSeq;
	private int feedId; 
	private String hashtagContent;
	
	public int getHashtagSeq() {
		return hashtagSeq;
	}
	public void setHashtagSeq(int hashtagSeq) {
		this.hashtagSeq = hashtagSeq;
	}
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	public String getHashtagContent() {
		return hashtagContent;
	}
	public void setHashtagContent(String hashtagContent) {
		this.hashtagContent = hashtagContent;
	}
	
	@Override
	public String toString() {
		return "HashtagDto [hashtagSeq=" + hashtagSeq + ", feedId=" + feedId + ", hashtagContent=" + hashtagContent
				+ "]";
	}
	
	
	
	
}
