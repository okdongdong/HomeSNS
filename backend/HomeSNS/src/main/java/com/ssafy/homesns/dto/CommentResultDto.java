package com.ssafy.homesns.dto;

import java.util.List;

public class CommentResultDto {
	
	private int result;
	private List<CommentDto> commentList;
	
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
	@Override
	public String toString() {
		return "CommentResultDto [result=" + result + ", commentList=" + commentList + "]";
	}
	
	
	
	
}
