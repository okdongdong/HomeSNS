package com.ssafy.homesns.dto;

import java.util.Date;
import java.util.List;

public class CommentDto {

	private int commentId;
	private int feedId; 
	private int commentTagSeq;
	private String commentTagName; 
	private int commentAuthorSeq;
	private String commentAuthor;
	private String commentContent;
	private Date commentUploadDate;
	
	private String commentUserProfileImageUrl;
	
	private List<Integer> commentTags;
	private List<String> commentTagNameList;
	private int good;
	private int sad;
	private int check; 
	private int fun;
	private int amaze;
	
	private String code;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}

	public int getCommentTagSeq() {
		return commentTagSeq;
	}

	public void setCommentTagSeq(int commentTagSeq) {
		this.commentTagSeq = commentTagSeq;
	}

	public String getCommentTagName() {
		return commentTagName;
	}

	public void setCommentTagName(String commentTagName) {
		this.commentTagName = commentTagName;
	}

	public int getCommentAuthorSeq() {
		return commentAuthorSeq;
	}

	public void setCommentAuthorSeq(int commentAuthorSeq) {
		this.commentAuthorSeq = commentAuthorSeq;
	}

	public String getCommentAuthor() {
		return commentAuthor;
	}

	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentUploadDate() {
		return commentUploadDate;
	}

	public void setCommentUploadDate(Date commentUploadDate) {
		this.commentUploadDate = commentUploadDate;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Integer> getCommentTags() {
		return commentTags;
	}

	public void setCommentTags(List<Integer> commentTags) {
		this.commentTags = commentTags;
	}

	public List<String> getCommentTagNameList() {
		return commentTagNameList;
	}

	public void setCommentTagNameList(List<String> commentTagNameList) {
		this.commentTagNameList = commentTagNameList;
	}
	
	

	public String getCommentUserProfileImageUrl() {
		return commentUserProfileImageUrl;
	}

	public void setCommentUserProfileImageUrl(String commentUserProfileImageUrl) {
		this.commentUserProfileImageUrl = commentUserProfileImageUrl;
	}

	@Override
	public String toString() {
		return "CommentDto [commentId=" + commentId + ", feedId=" + feedId + ", commentTagSeq=" + commentTagSeq
				+ ", commentTagName=" + commentTagName + ", commentAuthorSeq=" + commentAuthorSeq + ", commentAuthor="
				+ commentAuthor + ", commentContent=" + commentContent + ", commentUploadDate=" + commentUploadDate
				+ ", commentUserProfileImageUrl=" + commentUserProfileImageUrl + ", commentTags=" + commentTags
				+ ", commentTagNameList=" + commentTagNameList + ", good=" + good + ", sad=" + sad + ", check=" + check
				+ ", fun=" + fun + ", amaze=" + amaze + ", code=" + code + "]";
	}



	
}
