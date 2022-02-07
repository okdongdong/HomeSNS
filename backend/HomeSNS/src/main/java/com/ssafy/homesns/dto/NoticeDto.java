package com.ssafy.homesns.dto;

import java.util.List;

public class NoticeDto {
    private int noticeId;
    private int userSeq;
    private String userName, userProfileUrl;
    private int targetUserSeq;
    private int targetUserGroupId;
    private String noticeType;
    private int noticeContentId;
    private String noticeReadYn;
    private List<Integer> targetUserList;
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserProfileUrl() {
		return userProfileUrl;
	}
	public void setUserProfileUrl(String userProfileUrl) {
		this.userProfileUrl = userProfileUrl;
	}
	public int getTargetUserSeq() {
		return targetUserSeq;
	}
	public void setTargetUserSeq(int targetUserSeq) {
		this.targetUserSeq = targetUserSeq;
	}
	public int getTargetUserGroupId() {
		return targetUserGroupId;
	}
	public void setTargetUserGroupId(int targetUserGroupId) {
		this.targetUserGroupId = targetUserGroupId;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public int getNoticeContentId() {
		return noticeContentId;
	}
	public void setNoticeContentId(int noticeContentId) {
		this.noticeContentId = noticeContentId;
	}
	public String getNoticeReadYn() {
		return noticeReadYn;
	}
	public void setNoticeReadYn(String noticeReadYn) {
		this.noticeReadYn = noticeReadYn;
	}
	public List<Integer> getTargetUserList() {
		return targetUserList;
	}
	public void setTargetUserList(List<Integer> targetUserList) {
		this.targetUserList = targetUserList;
	}
	public int getNoticeId() {
		return noticeId;
	}
	@Override
	public String toString() {
		return "NoticeDto [noticeId=" + noticeId + ", userSeq=" + userSeq + ", userName=" + userName
				+ ", userProfileUrl=" + userProfileUrl + ", targetUserSeq=" + targetUserSeq + ", targetUserGroupId="
				+ targetUserGroupId + ", noticeType=" + noticeType + ", noticeContentId=" + noticeContentId
				+ ", noticeReadYn=" + noticeReadYn + ", targetUserList=" + targetUserList + "]";
	}

}
