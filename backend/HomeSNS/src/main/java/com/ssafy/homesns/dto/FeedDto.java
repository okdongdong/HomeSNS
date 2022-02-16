package com.ssafy.homesns.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class FeedDto {
	
	private int feedId;
	private int groupId;
	private int feedAuthorSeq;
	private String feedAuthor;
	private String authorProfileImageUrl;
	private String feedTitle; 
	private String feedContent;
	private String feedHashtags;
	private String feedAttendees;
	private int feedLocationId;
	private Date feedUploadDate;

	private String feedLocationStr; 
	private String fileDeleteStr; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate feedEventDate;
 
	private LocationDto locationDto;
    
	private int good;
	private int sad;
	private int check; 
	private int fun;
	private int amaze;
	private String code;
	private String scrapYn; 
	
	private List<FileDto> fileList;
	private List<CommentDto> commentList;
	private List<HashtagDto> hashtagList;
	private List<UserDto> userList;
	
	private boolean timeline;
	
	
	
	public boolean isTimeline() {
		return timeline;
	}
	public void setTimeline(boolean timeline) {
		this.timeline = timeline;
	}
	
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getFeedAuthorSeq() {
		return feedAuthorSeq;
	}
	public void setFeedAuthorSeq(int feedAuthorSeq) {
		this.feedAuthorSeq = feedAuthorSeq;
	}
	public String getFeedAuthor() {
		return feedAuthor;
	}
	public void setFeedAuthor(String feedAuthor) {
		this.feedAuthor = feedAuthor;
	}
	
	
	public String getAuthorProfileImageUrl() {
		return authorProfileImageUrl;
	}
	public void setAuthorProfileImageUrl(String authorProfileImageUrl) {
		this.authorProfileImageUrl = authorProfileImageUrl;
	}
	public String getFeedTitle() {
		return feedTitle;
	}
	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}
	public String getFeedContent() {
		return feedContent;
	}
	public void setFeedContent(String feedContent) {
		this.feedContent = feedContent;
	}
	public String getFeedHashtags() {
		return feedHashtags;
	}
	public void setFeedHashtags(String feedHashtags) {
		this.feedHashtags = feedHashtags;
	}
	public Date getFeedUploadDate() {
		return feedUploadDate;
	}
	public void setFeedUploadDate(Date feedUploadDate) {
		this.feedUploadDate = feedUploadDate;
	}
	public LocalDate getFeedEventDate() {
		return feedEventDate;
	}
	public void setFeedEventDate(LocalDate feedEventDate) {
		this.feedEventDate = feedEventDate;
	}
	
	public String getFeedAttendees() {
		return feedAttendees;
	}
	public void setFeedAttendees(String feedAttendees) {
		this.feedAttendees = feedAttendees;
	}
	
	public int getFeedLocationId() {
		return feedLocationId;
	}
	public void setFeedLocationId(int feedLocationId) {
		this.feedLocationId = feedLocationId;
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
	public List<FileDto> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileDto> fileList) {
		this.fileList = fileList;
	}
	public List<CommentDto> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentDto> commentList) {
		this.commentList = commentList;
	}
	public List<HashtagDto> getHashtagList() {
		return hashtagList;
	}
	public void setHashtagList(List<HashtagDto> hashtagList) {
		this.hashtagList = hashtagList;
	}
	public LocationDto getLocationDto() {
		return locationDto;
	}
	public void setLocationDto(LocationDto locationDto) {
		this.locationDto = locationDto;
	}
	public List<UserDto> getUserList() {
		return userList;
	}
	public void setUserList(List<UserDto> userList) {
		this.userList = userList;
	}
	
	
	public String getFileDeleteStr() {
		return fileDeleteStr;
	}
	public void setFileDeleteStr(String fileDeleteStr) {
		this.fileDeleteStr = fileDeleteStr;
	}
	public String getFeedLocationStr() {
		return feedLocationStr;
	}
	public void setFeedLocationStr(String feedLocationStr) {
		this.feedLocationStr = feedLocationStr;
	}
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getScrapYn() {
		return scrapYn;
	}
	public void setScrapYn(String scrapYn) {
		this.scrapYn = scrapYn;
	}
	@Override
	public String toString() {
		return "FeedDto [feedId=" + feedId + ", groupId=" + groupId + ", feedAuthorSeq=" + feedAuthorSeq
				+ ", feedAuthor=" + feedAuthor + ", authorProfileImageUrl=" + authorProfileImageUrl + ", feedTitle="
				+ feedTitle + ", feedContent=" + feedContent + ", feedHashtags=" + feedHashtags + ", feedAttendees="
				+ feedAttendees + ", feedLocationId=" + feedLocationId + ", feedUploadDate=" + feedUploadDate
				+ ", feedLocationStr=" + feedLocationStr + ", fileDeleteStr=" + fileDeleteStr + ", feedEventDate="
				+ feedEventDate + ", locationDto=" + locationDto + ", good=" + good + ", sad=" + sad + ", check="
				+ check + ", fun=" + fun + ", amaze=" + amaze + ", code=" + code + ", scrapYn=" + scrapYn
				+ ", fileList=" + fileList + ", commentList=" + commentList + ", hashtagList=" + hashtagList
				+ ", userList=" + userList + ", timeline=" + timeline + "]";
	}
	
	
	

}
