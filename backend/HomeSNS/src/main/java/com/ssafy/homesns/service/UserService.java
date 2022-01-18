package com.ssafy.homesns.service;

import com.ssafy.homesns.dto.UserDto;
import com.ssafy.homesns.dto.UserResultDto;

public interface UserService {
	public UserResultDto userRegister(UserDto userDto);
	public UserResultDto userUpdate(UserDto userDto);
	public UserResultDto userDelete(int userSeq);
	public UserResultDto userExist(UserDto userDto);
}
