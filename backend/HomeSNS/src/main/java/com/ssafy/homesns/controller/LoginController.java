package com.ssafy.homesns.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homesns.dto.UserDto;
import com.ssafy.homesns.service.LoginService;
import com.ssafy.homesns.service.UserService;

@RestController
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@Autowired
	UserService userService;
	
	private static final int SUCCESS = 1;
	
	// json request (body / payload)
	// request content type : application/json ==> @RequestBody 사용
	@PostMapping(value="/login")
	public ResponseEntity<UserDto> login(@RequestBody UserDto dto, HttpSession session) {
		System.out.println("로그인 시도");
		UserDto userDto = loginService.login(dto);
		// 로그인이 성공한다면 session에 현재 유저 정보를 저장한다
		if (userDto != null) {
			session.setAttribute("userDto", userDto);
			System.out.println("로그인 성공");
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		} else {
			System.out.println("로그인 실패");
			return new ResponseEntity<UserDto>(userDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/logout")
	public ResponseEntity<UserDto> logout(HttpSession session) {
		UserDto userDto = null;
		System.out.println("로그아웃");
		session.invalidate();
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
}
