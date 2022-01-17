package com.ssafy.homesns.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.homesns.dao.UserDao;
import com.ssafy.homesns.dto.UserDto;
import com.ssafy.homesns.dto.UserResultDto;

public class UserServiceImpl implements UserService{

	
	@Autowired
	UserDao userDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	// userDao의 userRegister를 사용해서 DB에 insert한다
	// 성공하면 Result를 SUCCESS로 set하고, 실패하면 Result를 FAIL로 set한다
	@Override
	public UserResultDto userRegister(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if ( userDao.userRegister(userDto) == 1 ) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userUpdate(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if ( userDao.userUpdate(userDto) == 1 ) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userDelete(int userSeq) {
		UserResultDto userResultDto = new UserResultDto();
		if ( userDao.userDelete(userSeq) == 1 ) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userExist(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if ( userDao.userExist(userDto) == 1 ) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

}