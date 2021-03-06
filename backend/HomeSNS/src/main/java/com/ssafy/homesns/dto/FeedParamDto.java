package com.ssafy.homesns.dto;

public class FeedParamDto {

	//infinity scroll parameter
	private int limit;
	private int offset; 
	
	// group에 해당하는 feed검색용 parameter
	private int groupId;

	// userSeq를 사용한 feed검색용 parameter
	private int userSeq;
	
	
	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

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
		return "FeedParamDto [limit=" + limit + ", offset=" + offset + ", groupId=" + groupId + ", userSeq=" + userSeq
				+ "]";
	} 

}
