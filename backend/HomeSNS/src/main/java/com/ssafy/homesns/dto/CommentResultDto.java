package com.ssafy.homesns.dto;

import java.util.List;

public class CommentResultDto {
	
	private int result;
	private List<CommentDto> commentList;
	private CommentDto comment;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
	public List<CommentDto> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentDto> commentList) {
		this.commentList = commentList;
	}
	
	
	
	public CommentDto getComment() {
		return comment;
	}
	public void setComment(CommentDto comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "CommentResultDto [result=" + result + ", commentList=" + commentList + ", comment=" + comment + "]";
	}
	
	
	
	
}
