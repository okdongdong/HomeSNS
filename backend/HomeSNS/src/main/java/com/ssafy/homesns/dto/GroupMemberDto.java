package com.ssafy.homesns.dto;

public class GroupMemberDto {
	private int groupId;
	private int userSeq;
	private String userName;
	private String managerYn;
	
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
	public String getManagerYn() {
		return managerYn;
	}
	public void setManagerYn(String managerYn) {
		this.managerYn = managerYn;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "GroupMemberDto [groupId=" + groupId + ", userSeq=" + userSeq + ", managerYn=" + managerYn + "]";
	}

}
