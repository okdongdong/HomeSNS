package com.ssafy.homesns.dto;

import java.util.List;

public class CommentResultDto {
	
	private int result;
	private int count;
	private List<CommentDto> commentList;
	private int commentResultDto; 
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<CommentDto> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentDto> commentList) {
		this.commentList = commentList;
	}
	
	
	
	public int getCommentResultDto() {
		return commentResultDto;
	}
	public void setCommentResultDto(int commentResultDto) {
		this.commentResultDto = commentResultDto;
	}
	@Override
	public String toString() {
		return "CommentResultDto [result=" + result + ", count=" + count + ", commentList=" + commentList
				+ ", commentResultDto=" + commentResultDto + "]";
	}
	
	
}
