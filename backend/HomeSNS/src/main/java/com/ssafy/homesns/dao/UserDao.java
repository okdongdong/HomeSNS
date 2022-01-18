package com.ssafy.homesns.dao;

import com.ssafy.homesns.dto.UserDto;

public interface UserDao {
	// userDto를 받아서 DB에 유저 정보를 저장한다
	public int userRegister(UserDto userDto);
	
	// userDto를 받아서 DB에 유저 정보를 수정한다
	public int userUpdate(UserDto userDto);
	
	// userSeq를 받아서 DB에 유저 정보를 삭제한다
	public int userDelete(int userSeq);
	
	// userDto를 받아서 DB에 유저 정보가 있는지 확인한다
	public int userExist(UserDto userDto);
}
