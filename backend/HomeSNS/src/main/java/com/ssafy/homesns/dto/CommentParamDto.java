package com.ssafy.homesns.dto;

public class CommentParamDto {

	//infinity scroll parameter
	private int limit;
	private int offset; 
	private int feedId;
	private int userSeq;
	
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
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
		return "CommentParamDto [limit=" + limit + ", offset=" + offset + ", feedId=" + feedId + ", userSeq=" + userSeq
				+ "]";
	}
	
	
	
	
	
}
