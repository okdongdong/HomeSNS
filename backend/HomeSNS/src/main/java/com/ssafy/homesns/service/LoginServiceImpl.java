package com.ssafy.homesns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.homesns.dao.LoginDao;
import com.ssafy.homesns.dto.UserDto;

@Service
public class LoginServiceImpl implements LoginService{


	@Autowired
	LoginDao loginDao;

	// parameter로 받은 dto와 dto의 아이디를 사용해서 db에 접근하여 얻은 유저 정보를 비교한다
	// 비교한 결과, 비밀번호가 일치한다면 유저 정보를 return한다
	@Override
	public UserDto login(UserDto dto) {
		// userDto ==> DB에 저장되어 있는 유저정보
		// dto ==> 프론트에서 전달받은 로그인 정보
		UserDto userDto = loginDao.login(dto.getUserId());
		if ( userDto != null && userDto.getUserPassword().equals(dto.getUserPassword()) ) {
			return userDto;
		} else {
			return null;
		}
	}
	
}
