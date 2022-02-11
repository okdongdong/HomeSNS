package com.ssafy.homesns.dto;

public class GameParamDto {

	// Infinity Scroll Parameter
	private int limit;
	private int offset;
	
	// groupo에 해당하는 Game 검색용 Parameter
	private int groupId;

	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "GameParamDto [limit=" + limit + ", offset=" + offset + ", groupId=" + groupId + "]";
	}
	
	
}
