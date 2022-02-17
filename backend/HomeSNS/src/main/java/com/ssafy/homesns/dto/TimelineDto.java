package com.ssafy.homesns.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class TimelineDto {

	private int feedId;
	private String feedTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate feedEventDate;
    
    private int feedLocationId;
    private String feedLocation;
    
    private List<MainFileDto> fileList;
    private List<UserDto> userDtoList;
    private List<HashtagDto> hashtagDtoList;
    
    private int feedAuthor;
    private String userProfileImageUrl;
    
    
	public int getFeedLocationId() {
		return feedLocationId;
	}
	public void setFeedLocationId(int feedLocationId) {
		this.feedLocationId = feedLocationId;
	}
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	public String getFeedTitle() {
		return feedTitle;
	}
	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}
	public LocalDate getFeedEventDate() {
		return feedEventDate;
	}
	public void setFeedEventDate(LocalDate feedEventDate) {
		this.feedEventDate = feedEventDate;
	}
	public String getFeedLocation() {
		return feedLocation;
	}
	public void setFeedLocation(String feedLocation) {
		this.feedLocation = feedLocation;
	}
	public List<MainFileDto> getFileList() {
		return fileList;
	}
	public void setFileList(List<MainFileDto> fileList) {
		this.fileList = fileList;
	}
	public List<UserDto> getUserDtoList() {
		return userDtoList;
	}
	public void setUserDtoList(List<UserDto> userDtoList) {
		this.userDtoList = userDtoList;
	}
	public List<HashtagDto> getHashtagDtoList() {
		return hashtagDtoList;
	}
	public void setHashtagDtoList(List<HashtagDto> hashtagDtoList) {
		this.hashtagDtoList = hashtagDtoList;
	}
	public int getFeedAuthor() {
		return feedAuthor;
	}
	public void setFeedAuthor(int feedAuthor) {
		this.feedAuthor = feedAuthor;
	}
	public String getUserProfileImageUrl() {
		return userProfileImageUrl;
	}
	public void setUserProfileImageUrl(String userProfileImageUrl) {
		this.userProfileImageUrl = userProfileImageUrl;
	}
	
	@Override
	public String toString() {
		return "TimelineDto [feedId=" + feedId + ", feedTitle=" + feedTitle + ", feedEventDate=" + feedEventDate
				+ ", feedLocationId=" + feedLocationId + ", feedLocation=" + feedLocation + ", fileList=" + fileList
				+ ", userDtoList=" + userDtoList + ", hashtagDtoList=" + hashtagDtoList + ", feedAuthor=" + feedAuthor
				+ ", userProfileImageUrl=" + userProfileImageUrl + "]";
	}
	
}
