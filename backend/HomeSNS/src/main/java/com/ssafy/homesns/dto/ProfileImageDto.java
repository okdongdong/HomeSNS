package com.ssafy.homesns.dto;

import java.util.Date;

public class ProfileImageDto {
	
	private int profileImageId;
	private int userSeq;
	private int groupId;
	private String groupName;
	
	private String profileImageName;
	private long profileImageSize;
	private String profileImageUrl;
	private Date regDt;
	
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getProfileImageId() {
		return profileImageId;
	}
	public void setProfileImageId(int profileImageId) {
		this.profileImageId = profileImageId;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getProfileImageName() {
		return profileImageName;
	}
	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}
	public long getProfileImageSize() {
		return profileImageSize;
	}
	public void setProfileImageSize(long profileImageSize) {
		this.profileImageSize = profileImageSize;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	
	@Override
	public String toString() {
		return "ProfileImageDto [profileImageId=" + profileImageId + ", userSeq=" + userSeq + ", groupId=" + groupId
				+ ", groupName=" + groupName + ", profileImageName=" + profileImageName + ", profileImageSize="
				+ profileImageSize + ", profileImageUrl=" + profileImageUrl + ", regDt=" + regDt + "]";
	}
	
}
