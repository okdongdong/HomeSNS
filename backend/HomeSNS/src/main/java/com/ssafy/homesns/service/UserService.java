package com.ssafy.homesns.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.homesns.dto.FeedParamDto;
import com.ssafy.homesns.dto.MainFeedResultDto;
import com.ssafy.homesns.dto.UserDto;
import com.ssafy.homesns.dto.UserResultDto;

public interface UserService {
	public UserResultDto userRegister(UserDto userDto, MultipartHttpServletRequest request);
	public UserResultDto profileImageUpdate( MultipartHttpServletRequest request);
	
	public UserResultDto userMypageSearch();
	
	public UserResultDto passwordCheck(UserDto userDto);
	public UserResultDto userInfoUpdate(UserDto userDto);
	public UserResultDto userPasswordUpdate(UserDto userDto);
	
	public UserResultDto userDelete(int userSeq);
	public UserResultDto userExist(String userPhone);
	public UserResultDto userPage(int userSeq);
	
	// 중복체크
	public UserResultDto checkUserId(String userId);
	public UserResultDto checkUserEmail(String userEmail);
	public UserResultDto checkUserPhone(String userPhone);

	// 유저/타유저 프로필 페이지
	public MainFeedResultDto userFeedSearch(FeedParamDto feedParamDto);
	public MainFeedResultDto userScrapSearch(FeedParamDto feedParamDto);
	
	// 아이디/비밀번호 찾기
	public UserResultDto userFindId(String userEmail);
	public UserResultDto userFindPw(UserDto userDto);
}
