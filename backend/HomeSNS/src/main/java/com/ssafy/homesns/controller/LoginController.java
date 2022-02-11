package com.ssafy.homesns.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homesns.dto.LoginDto;
import com.ssafy.homesns.dto.TokenDto;
import com.ssafy.homesns.dto.UserDto;
import com.ssafy.homesns.jwt.JwtFilter;
import com.ssafy.homesns.jwt.TokenProvider;
import com.ssafy.homesns.service.CustomUserDetailsService;

@CrossOrigin(
		origins = "http://localhost:5500", // npm에서 5500번을 사용한다
		allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })
@RestController
public class LoginController {
	
	@Autowired
	CustomUserDetailsService service;
	
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	public LoginController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
	}

	@PostMapping("/login")
	public ResponseEntity<UserDto> authorize(@RequestBody LoginDto loginDto) {
		System.out.println("Enter /login API");
		// userId, userPassword를 파라미터로 받아서 UsernamePasswordAuthenticationToken을 생성한다
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getUserPassword());
		System.out.println("authenticationToken : " + authenticationToken);

		// 암호화된 값 확인
		// BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		// System.out.println(b.encode("1234"));

		// 토큰으로 Authentication 객체를 생성하려고 authentication 메소드가 실행 될 때, loadUserByUsername 메소드가 실행된다
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		
		// 결과값으로 생성된 객체를 SecurityContext에 저장한다
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("authentication : " + authentication);
		
		// 인증정보를 기준으로 tokenProvider의 createToken을 통해서 JWT토큰을 생성한다
		String jwt = tokenProvider.createToken(authentication);
		System.out.println("jwt : " + jwt);

		// 토큰을 헤더에 넣어준다
		HttpHeaders httpHeaders = new HttpHeaders();
		// CORS이슈
		httpHeaders.add("Access-Control-Expose-Headers", "AUTHORIZATION");
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		System.out.println("httpHeaders : " + httpHeaders);
		
		// 유저 정보를 DB에서 받아온다
		UserDto userDto = service.getUserInfo(loginDto.getUserId());
		
		return new ResponseEntity<>(userDto, httpHeaders, HttpStatus.OK);
	}
	
	// test 용도
	@PostMapping("/hello")
	public ResponseEntity<String> hello(@RequestBody LoginDto loginDto) {
		System.out.println("Enter Hello");
		System.out.println(loginDto.getUserId());
		System.out.println(loginDto.getUserPassword());
		return new ResponseEntity<String>("HELLO!!!", HttpStatus.OK);
	}
}
