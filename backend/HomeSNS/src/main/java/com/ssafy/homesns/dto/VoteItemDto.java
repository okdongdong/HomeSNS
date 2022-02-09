package com.ssafy.homesns.dto;

public class VoteItemDto {

	private int voteItemId;
	private String voteItemName;
	private int gameId;
	private int count;
	
	// 현재 유저가 투표를 했다면, 투표를 한 항목에 userSeq 추가
	private int userSeq;

	public int getVoteItemId() {
		return voteItemId;
	}

	public void setVoteItemId(int voteItemId) {
		this.voteItemId = voteItemId;
	}

	public String getVoteItemName() {
		return voteItemName;
	}

	public void setVoteItemName(String voteItemName) {
		this.voteItemName = voteItemName;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	@Override
	public String toString() {
		return "VoteItemDto [voteItemId=" + voteItemId + ", voteItemName=" + voteItemName + ", gameId=" + gameId
				+ ", count=" + count + ", userSeq=" + userSeq + "]";
	}

}
