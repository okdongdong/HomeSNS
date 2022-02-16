package com.ssafy.homesns.dto;

public class FeedEmotionDto {

	private int feedId;
	private int userSeq;
	
	private int good;
	private int sad;
	private int check; 
	private int fun;
	private int amaze;

	private String code;

	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getSad() {
		return sad;
	}
	public void setSad(int sad) {
		this.sad = sad;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	public int getFun() {
		return fun;
	}
	public void setFun(int fun) {
		this.fun = fun;
	}
	public int getAmaze() {
		return amaze;
	}
	public void setAmaze(int amaze) {
		this.amaze = amaze;
	}
	@Override
	public String toString() {
		return "FeedEmotionDto [feedId=" + feedId + ", userSeq=" + userSeq + ", good=" + good + ", sad=" + sad
				+ ", check=" + check + ", fun=" + fun + ", amaze=" + amaze + ", code=" + code + "]";
	}
	
	
}
