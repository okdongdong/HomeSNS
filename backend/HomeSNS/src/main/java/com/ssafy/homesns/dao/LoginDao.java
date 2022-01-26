package com.ssafy.homesns.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homesns.dto.CustomUserDetails;

@Mapper
public interface LoginDao {
	// 토큰을 사용하는 로그인 방식
	// 아이디를 받아서 비밀번호를 return한다
	// 비밀번호를 받는 동작에 굳이 userDto를 사용할 필요는 없음
	public CustomUserDetails login(String userId);
}
