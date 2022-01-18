package com.ssafy.homesns.dto;

public class GroupDto {
	private int groupId;
	private String groupName;
	private String groupProfileImageUrl;
	private int groupLeaderSeq;
	private String groupPassword;
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupProfileImageUrl() {
		return groupProfileImageUrl;
	}
	public void setGroupProfileImageUrl(String groupProfileImageUrl) {
		this.groupProfileImageUrl = groupProfileImageUrl;
	}
	public int getGroupLeaderSeq() {
		return groupLeaderSeq;
	}
	public void setGroupLeaderSeq(int groupLeaderSeq) {
		this.groupLeaderSeq = groupLeaderSeq;
	}
	public String getGroupPassword() {
		return groupPassword;
	}
	public void setGroupPassword(String groupPassword) {
		this.groupPassword = groupPassword;
	}
	
	@Override
	public String toString() {
		return "GroupDto [groupId=" + groupId + ", groupName=" + groupName + ", groupProfileImageUrl="
				+ groupProfileImageUrl + ", groupLeaderSeq=" + groupLeaderSeq + ", groupPassword=" + groupPassword
				+ "]";
	}
	
}
