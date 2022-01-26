package com.ssafy.homesns.dto;

public class LoginDto {
	private String userId;
	private String userPassword;
	
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
	
	@Override
	public String toString() {
		return "LoginDto [userId=" + userId + ", userPassword=" + userPassword + "]";
	}
	
}
