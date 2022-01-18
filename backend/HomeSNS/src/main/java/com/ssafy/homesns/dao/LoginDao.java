package com.ssafy.homesns.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homesns.dto.UserDto;

@Mapper
public interface LoginDao {
	// 아이디를 사용해서 DB에 일치하는 유저 정보를 찾는다
	public UserDto login(String userId);

}
