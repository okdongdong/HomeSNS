package com.ssafy.homesns.dto;


public class NoticeResultDto {

    private int userSeq, noticeId, noticeContentId;
    private String noticeType, userName, userProfileImageUrl, noticeReadYn, noticeTime;
    private String noticeContentTitle, noticeContentContent;

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public int getNoticeContentId() {
        return noticeContentId;
    }

    public void setNoticeContentId(int noticeContentId) {
        this.noticeContentId = noticeContentId;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfileImageUrl() {
        return userProfileImageUrl;
    }

    public void setUserProfileImageUrl(String userProfileImageUrl) {
        this.userProfileImageUrl = userProfileImageUrl;
    }

    public String getNoticeReadYn() {
        return noticeReadYn;
    }

    public void setNoticeReadYn(String noticeReadYn) {
        this.noticeReadYn = noticeReadYn;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getNoticeContentTitle() {
        return noticeContentTitle;
    }

    public void setNoticeContentTitle(String noticeContentTitle) {
        this.noticeContentTitle = noticeContentTitle;
    }

    public String getNoticeContentContent() {
        return noticeContentContent;
    }

    public void setNoticeContentContent(String noticeContentContent) {
        this.noticeContentContent = noticeContentContent;
    }
}
