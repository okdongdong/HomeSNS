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
	private String feedTitle; 
	private String feedContent;
	private Date feedUploadDate;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate feedEventDate;
    
	private String feedLocation;
	
	private int good;
	private int sad;
	private int check; 
	private int fun;
	private int amaze;
	
	private List<FileDto> fileList;
	private List<CommentDto> commentList;
	private List<HashtagDto> hashtagList;
	
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
	public String getFeedLocation() {
		return feedLocation;
	}
	public void setFeedLocation(String feedLocation) {
		this.feedLocation = feedLocation;
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
	@Override
	public String toString() {
		return "FeedDto [feedId=" + feedId + ", groupId=" + groupId + ", feedAuthor=" + feedAuthor + ", feedTitle="
				+ feedTitle + ", feedContent=" + feedContent + ", feedUploadDate=" + feedUploadDate + ", feedEventDate="
				+ feedEventDate + ", feedLocation=" + feedLocation + ", good=" + good + ", sad=" + sad + ", check="
				+ check + ", fun=" + fun + ", amaze=" + amaze + ", fileList=" + fileList + ", commentList="
				+ commentList + ", hashtagList=" + hashtagList + "]";
	}
	
	
	
	

}
