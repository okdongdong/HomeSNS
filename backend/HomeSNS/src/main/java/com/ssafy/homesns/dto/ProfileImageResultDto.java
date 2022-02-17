package com.ssafy.homesns.dto;

public class ProfileImageResultDto {
	
	private int result;
	private ProfileImageDto profileImageDto;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public ProfileImageDto getProfileImageDto() {
		return profileImageDto;
	}
	public void setProfileImageDto(ProfileImageDto profileImageDto) {
		this.profileImageDto = profileImageDto;
	}
	@Override
	public String toString() {
		return "ProfileImageResultDto [result=" + result + ", profileImageDto=" + profileImageDto + "]";
	}
	
	
}
