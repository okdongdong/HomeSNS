package com.ssafy.homesns.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homesns.dto.UserDto;
import com.ssafy.homesns.dto.UserResultDto;
import com.ssafy.homesns.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	private static final int SUCCESS = 1;
	
	// POST 통신으로 /register에 요청이 올 경우 동작한다
	// userRegister를 실행 후, 그 결과를 userResultDto에 받는다
	// getResult()에 따라 Html코드로 등록을 성공했는지 실패했는지 return한다
	@PostMapping(value="/register")
	public ResponseEntity<UserResultDto> register(@RequestBody UserDto userDto) {
		UserResultDto userResultDto = userService.userRegister(userDto);
		if ( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		}
	}
	
	
	// 마이페이지 조회
	@GetMapping(value="/mypage")
	public UserDto mypage(UserDto dto, HttpSession session) {
		
		UserDto userDto = (UserDto) session.getAttribute("userDto"); 
		
		return userDto; 
	}
	
	
	
	// 마이페이지 수정
	@PutMapping(value="/mypage")
	public ResponseEntity<UserResultDto> update(@RequestBody UserDto userDto) {
		System.out.print("update - mypage");
		System.out.println(userDto);
		UserResultDto userResultDto = userService.userUpdate(userDto);
		if ( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		}
	}
	
	// 회원 탈퇴
	@DeleteMapping(value="/register/{userSeq}")
	public ResponseEntity<UserResultDto> delete(@PathVariable int userSeq) {
		System.out.println("[Delete] - " + userSeq);
		UserResultDto userResultDto = userService.userDelete(userSeq);
		if ( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		}
	}
	
	// 중복 체크 아님 
	// 핸드폰 번호를 받아와서 해당유저 검색후 유저정보 다 넘겨줌 -> 프런트에서 원하는대로 가공해서 쓸 수 있게 
	@GetMapping(value="/user/{phone}")
	public ResponseEntity<UserResultDto> exist(@PathVariable String phone){
		System.out.println("[Exist] - " + phone);
		UserResultDto userResultDto = userService.userExist(phone);
		
		if ( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	// 중복 체크용 코드 
	// id, email, phone 각각 3가지 만들 것
	
	@GetMapping (value="/register/{id}")
	public UserResultDto checkId(@PathVariable String id){
		System.out.println("[isId] - " + id);
		
		UserResultDto userResultDto = userService.checkUserId(id);
		
		return userResultDto;

	}
	@GetMapping (value="/register/{email}")
	public UserResultDto checkEmail(@PathVariable String email){
		System.out.println("[isEmail] - " + email);
		
		UserResultDto userResultDto = userService.checkUserEmail(email);
		
		return userResultDto;

	}
	
	
	@GetMapping (value="/register/{phone}")
	public UserResultDto checkPhone(@PathVariable String phone){
		System.out.println("[isPhone] - " + phone);
		
		UserResultDto userResultDto = userService.checkUserPhone(phone);
		
		return userResultDto;

	}
	
	
	
}