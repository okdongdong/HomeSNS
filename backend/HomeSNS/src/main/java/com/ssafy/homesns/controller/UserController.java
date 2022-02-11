package com.ssafy.homesns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.homesns.dto.FeedParamDto;
import com.ssafy.homesns.dto.MainFeedResultDto;
import com.ssafy.homesns.dto.UserDto;
import com.ssafy.homesns.dto.UserResultDto;
import com.ssafy.homesns.service.UserService;

@CrossOrigin(
		origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500", "http://192.168.0.40:5500" },
		allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })
@RestController
public class UserController {

	@Autowired
	UserService userService;

	private static final int SUCCESS = 1;
	private static final int NOEXIST = 2;
	
	//	// POST 통신으로 /register에 요청이 올 경우 동작한다
	//	// userRegister를 실행 후, 그 결과를 userResultDto에 받는다
	//	// getResult()에 따라 Html코드로 등록을 성공했는지 실패했는지 return한다
	//	@PostMapping(value="/register")
	//	public ResponseEntity<UserResultDto> register(@RequestBody UserDto userDto) {
	//		System.out.println(userDto);
	//		UserResultDto userResultDto = userService.userRegister(userDto);
	//		if ( userResultDto.getResult() == SUCCESS ) {
	//			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
	//		} else {
	//			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
	//		}
	//	}
	@PostMapping(value="/register")
	public ResponseEntity<UserResultDto> register(
			@ModelAttribute UserDto userDto,
			MultipartHttpServletRequest request) {

		UserResultDto userResultDto = userService.userRegister(userDto, request);

		if ( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	// 마이페이지 조회
	@GetMapping(value="/mypage")
	public ResponseEntity<UserResultDto> mypage() {

		UserResultDto userResultDto = userService.userMypageSearch();

		if ( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);

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

	// 유저 프로필사진 수정
	@PostMapping(value="/mypage/profileImage")
	public ResponseEntity<UserResultDto> userProfileImageUpdate(MultipartHttpServletRequest request) {

		UserResultDto userResultDto = userService.profileImageUpdate(request);

		if ( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
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
	@GetMapping(value="/user/{userPhone}")
	public ResponseEntity<UserResultDto> exist(@PathVariable String userPhone){
		System.out.println("[Exist] - " + userPhone);
		UserResultDto userResultDto = userService.userExist(userPhone);

		if ( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		}
	}

	// UserSeq를 받아서 해당 유저 검색 후 유저 정보 다 넘겨준다 -> 이름 + 프로필 사진
	@GetMapping(value="/user/{userSeq}")
	public ResponseEntity<UserResultDto> userPage(@PathVariable int userSeq){
		
		UserResultDto userResultDto = userService.userPage(userSeq);

		if ( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		}
	}


	// 중복 체크용 코드 
	// id, email, phone 각각 3가지 만들 것

	@GetMapping (value="/register/{userId}")
	public UserResultDto checkId(@PathVariable String userId){
		System.out.println("[isId] - " + userId);

		UserResultDto userResultDto = userService.checkUserId(userId);

		return userResultDto;

	}
	@GetMapping (value="/register/{userEmail}")
	public UserResultDto checkEmail(@PathVariable String userEmail){
		System.out.println("[isEmail] - " + userEmail);

		UserResultDto userResultDto = userService.checkUserEmail(userEmail);

		return userResultDto;

	}


	@GetMapping (value="/register/{userPhone}")
	public UserResultDto checkPhone(@PathVariable String userPhone){
		System.out.println("[isPhone] - " + userPhone);

		UserResultDto userResultDto = userService.checkUserPhone(userPhone);

		return userResultDto;

	}


	@GetMapping(value="/user/feed/my")
	public ResponseEntity<MainFeedResultDto> userMyFeedSearch(FeedParamDto feedParamDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		feedParamDto.setUserSeq(userSeq);
		
		MainFeedResultDto mainFeedResultDto = userService.userFeedSearch(feedParamDto);
		
		if ( mainFeedResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<MainFeedResultDto>(mainFeedResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<MainFeedResultDto>(mainFeedResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/user/scrap/my")
	public ResponseEntity<MainFeedResultDto> userMyScrapSearch(FeedParamDto feedParamDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		feedParamDto.setUserSeq(userSeq);
		
		MainFeedResultDto mainFeedResultDto = userService.userScrapSearch(feedParamDto);
		
		if ( mainFeedResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<MainFeedResultDto>(mainFeedResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<MainFeedResultDto>(mainFeedResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping(value="/user/feed/other")
	public ResponseEntity<MainFeedResultDto> userFeedSearch(FeedParamDto feedParamDto) {

		MainFeedResultDto mainFeedResultDto = userService.userFeedSearch(feedParamDto);
		
		if ( mainFeedResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<MainFeedResultDto>(mainFeedResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<MainFeedResultDto>(mainFeedResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/user/scrap/other")
	public ResponseEntity<MainFeedResultDto> userScrapSearch(FeedParamDto feedParamDto) {
		
		MainFeedResultDto mainFeedResultDto = userService.userScrapSearch(feedParamDto);
		
		if ( mainFeedResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<MainFeedResultDto>(mainFeedResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<MainFeedResultDto>(mainFeedResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// email로 조회해서 아이디 찾기
	@GetMapping(value="/user/find/id/{userEmail}")
	public ResponseEntity<UserResultDto> userFindId(@PathVariable String userEmail) {
		
		UserResultDto userResultDto = userService.userFindId(userEmail);
		
		if ( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else if ( userResultDto.getResult() == NOEXIST ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// id + email로 조회해서 비밀번호 찾기
	@GetMapping(value="/user/find/pw")
	public ResponseEntity<UserResultDto> userFindPw(UserDto userDto) {
		
		UserResultDto userResultDto = userService.userFindPw(userDto);

		if ( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else if ( userResultDto.getResult() == NOEXIST ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
