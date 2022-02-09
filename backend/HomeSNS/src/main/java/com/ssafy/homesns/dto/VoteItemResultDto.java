package com.ssafy.homesns.dto;

public class VoteItemResultDto {
	
	private int result;
	private VoteItemDto voteItemDto;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public VoteItemDto getVoteItemDto() {
		return voteItemDto;
	}
	public void setVoteItemDto(VoteItemDto voteItemDto) {
		this.voteItemDto = voteItemDto;
	}
	
	@Override
	public String toString() {
		return "VoteItemResultDto [result=" + result + ", voteItemDto=" + voteItemDto + "]";
	}
	
}
