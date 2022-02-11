package com.ssafy.homesns.dto;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserDto {
	private int userSeq;
	private String userId;
	private String userPassword;
	private String userEmail;
	private String userName;
	private String userNickname;
	private String userPhone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate userBod;
	private String userProfileImageUrl;
	private Date userRegisterDate;
	
	// 회원 구분
	private String userCls;

	// 세션 로그인용 refreshtoken
	private String refreshToken;

	
	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	
	public LocalDate getUserBod() {
		return userBod;
	}

	public void setUserBod(LocalDate userBod) {
		this.userBod = userBod;
	}

	public String getUserProfileImageUrl() {
		return userProfileImageUrl;
	}

	public void setUserProfileImageUrl(String userProfileImageUrl) {
		this.userProfileImageUrl = userProfileImageUrl;
	}

	public Date getUserRegisterDate() {
		return userRegisterDate;
	}

	public void setUserRegisterDate(Date userRegisterDate) {
		this.userRegisterDate = userRegisterDate;
	}

	public String getUserCls() {
		return userCls;
	}

	public void setUserCls(String userCls) {
		this.userCls = userCls;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		return "UserDto [userSeq=" + userSeq + ", userId=" + userId + ", userPassword=" + userPassword + ", userEmail="
				+ userEmail + ", userName=" + userName + ", userNickname=" + userNickname + ", userPhone=" + userPhone
				+ ", userBod=" + userBod + ", userProfileImageUrl=" + userProfileImageUrl + ", userRegisterDate="
				+ userRegisterDate + ", userCls=" + userCls + ", refreshToken=" + refreshToken + "]";
	}
	
}
