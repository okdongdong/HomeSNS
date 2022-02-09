package com.ssafy.homesns.dto;

import java.util.Date;
import java.util.List;

public class GameDto {
	private int gameId;
	private int groupId;
	private String gameTitle;
	private int gameAuthor;
	private String gameAuthorName;
	private String gameAuthorProfileImageUrl;
	
	private String code;
	
	private Date gameRegisterDate;
	
	private List<VoteItemDto> voteItemDtoList;
	private GhostLegDto ghostLegDto;
	
	// 더 이상 수정을 할 수 있는지 없는지에 대한 여부
	// 여기서 수정은 단순 제목 수정이 아니라, 투표 항목, 사다리타기 결과 같은 중요 데이터 관련
	// true => 사용 가능
	// false => 투표 종료로 인한 사용 불가능 and 사다리타기 게임은 딱히 수정할 게 없을 것
	private String updateYn;

	// 현재 유저가 투표한 항목
	private int voteItemId;

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public int getGameAuthor() {
		return gameAuthor;
	}

	public void setGameAuthor(int gameAuthor) {
		this.gameAuthor = gameAuthor;
	}

	public String getGameAuthorName() {
		return gameAuthorName;
	}

	public void setGameAuthorName(String gameAuthorName) {
		this.gameAuthorName = gameAuthorName;
	}

	public String getGameAuthorProfileImageUrl() {
		return gameAuthorProfileImageUrl;
	}

	public void setGameAuthorProfileImageUrl(String gameAuthorProfileImageUrl) {
		this.gameAuthorProfileImageUrl = gameAuthorProfileImageUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getGameRegisterDate() {
		return gameRegisterDate;
	}

	public void setGameRegisterDate(Date gameRegisterDate) {
		this.gameRegisterDate = gameRegisterDate;
	}

	public List<VoteItemDto> getVoteItemDtoList() {
		return voteItemDtoList;
	}

	public void setVoteItemDtoList(List<VoteItemDto> voteItemDtoList) {
		this.voteItemDtoList = voteItemDtoList;
	}

	public GhostLegDto getGhostLegDto() {
		return ghostLegDto;
	}

	public void setGhostLegDto(GhostLegDto ghostLegDto) {
		this.ghostLegDto = ghostLegDto;
	}

	public String getUpdateYn() {
		return updateYn;
	}

	public void setUpdateYn(String updateYn) {
		this.updateYn = updateYn;
	}

	public int getVoteItemId() {
		return voteItemId;
	}

	public void setVoteItemId(int voteItemId) {
		this.voteItemId = voteItemId;
	}

	@Override
	public String toString() {
		return "GameDto [gameId=" + gameId + ", groupId=" + groupId + ", gameTitle=" + gameTitle + ", gameAuthor="
				+ gameAuthor + ", gameAuthorName=" + gameAuthorName + ", gameAuthorProfileImageUrl="
				+ gameAuthorProfileImageUrl + ", code=" + code + ", gameRegisterDate=" + gameRegisterDate
				+ ", voteItemDtoList=" + voteItemDtoList + ", ghostLegDto=" + ghostLegDto + ", updateYn=" + updateYn
				+ ", voteItemId=" + voteItemId + "]";
	}
	
}
