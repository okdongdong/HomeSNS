package com.ssafy.homesns.dao;

import com.ssafy.homesns.dto.UserDto;

public interface UserDao {
	// userDto를 받아서 DB에 유저 정보를 저장한다
	public int userRegister(UserDto userDto);
	
	// userDto를 받아서 DB에 유저 정보를 수정한다
	public int userUpdate(UserDto userDto);
	
	// userSeq를 받아서 DB에 유저 정보를 삭제한다
	public int userDelete(int userSeq);
	
	// phone을 받아서 DB에 있는 유저 정보를 가져온다.
	public UserDto userExist(String phone);
	
	// id를 받아서 DB에 유저 정보가 있는지 확인한다
	public int checkUserId(String id);
	
	// email을 받아서 DB에 유저 정보가 있는지 확인한다
	public int checkUserEmail(String email);
	
	// phone을 받아서 DB에 유저 정보가 있는지 확인한다
	public int checkUserPhone(String phone);
}
