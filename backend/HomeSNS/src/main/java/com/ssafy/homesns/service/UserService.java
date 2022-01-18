package com.ssafy.homesns.service;

import com.ssafy.homesns.dto.UserDto;
import com.ssafy.homesns.dto.UserResultDto;

public interface UserService {
	public UserResultDto userRegister(UserDto userDto);
	public UserResultDto userUpdate(UserDto userDto);
	public UserResultDto userDelete(int userSeq);
	public UserResultDto userExist(String phone);
	
	// 중복체크
	public UserResultDto checkUserId(String id);
	public UserResultDto checkUserEmail(String email);
	public UserResultDto checkUserPhone(String phone);

}
