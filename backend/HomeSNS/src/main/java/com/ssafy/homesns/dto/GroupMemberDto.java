package com.ssafy.homesns.dto;

public class GroupMemberDto {
	private int groupId;
	private int userSeq;
	private char managerYn;
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public char getManagerYn() {
		return managerYn;
	}
	public void setManagerYn(char managerYn) {
		this.managerYn = managerYn;
	}
	
	@Override
	public String toString() {
		return "GroupMemberDto [groupId=" + groupId + ", userSeq=" + userSeq + ", managerYn=" + managerYn + "]";
	}
	
}
