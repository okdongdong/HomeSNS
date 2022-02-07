package com.ssafy.homesns.dto;


public class NoticePageDto {

    private int targetUserSeq;

    private String noticeType;

    private int noticeContentId;

	public int getTargetUserSeq() {
		return targetUserSeq;
	}

	public void setTargetUserSeq(int targetUserSeq) {
		this.targetUserSeq = targetUserSeq;
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

	@Override
	public String toString() {
		return "NoticePageDto [targetUserSeq=" + targetUserSeq + ", noticeType=" + noticeType + ", noticeContentId="
				+ noticeContentId + "]";
	}


}
