package com.ssafy.homesns.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.homesns.dto.FeedDto;
import com.ssafy.homesns.dto.FeedResultDto;
import com.ssafy.homesns.service.FeedService;

@RestController
public class FeedController {

	@Autowired
	FeedService feedService;
	
	private static final int SUCCESS = 1;

	// 현재 그룹의 feedList를 가져온다. -> mainPage 
	// 파라미터로 받은 groupId를 feedMainPage로 넘겨주고 feedResultDto 값을 넘겨받는다. 
	// feedResultDto에 feedList를 담아 리턴한다. 
	@GetMapping(value="/main/{gropId}") // feedParamDto로 변경해야함! 
	public ResponseEntity<FeedResultDto> mainPage(@PathVariable int groupId, HttpSession session){
		
		FeedResultDto feedResultDto = feedService.feedMainPage(groupId);
		
		if(feedResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<FeedResultDto>(feedResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<FeedResultDto>(feedResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	//feedId로 해당 feed 상세조회
	@GetMapping(value="/feed/{feedId}")
	public ResponseEntity<FeedResultDto> feedDetail(@PathVariable int feedId, HttpSession session){
		
		FeedResultDto feedResultDto = feedService.feedDetail(feedId);
		
		if(feedResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<FeedResultDto>(feedResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<FeedResultDto>(feedResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
				
	}
	
	// feed추가
	@PostMapping(value="/feed")
	public ResponseEntity<FeedResultDto> feedInsert(FeedDto feedDto, MultipartHttpServletRequest request) {
		
		// 프런트에서 넘겨줄때 feedDto에 groupId를 넘겨주면 필요없는 코드
		// feedDto.setGroupId( ((GroupDto) request.getSession().getAttribute("groupDto")).getGroupId());
		FeedResultDto feedResultDto = feedService.feedInsert(feedDto, request);
		
		if( feedResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<FeedResultDto>(feedResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<FeedResultDto>(feedResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	// feed 수정
	@PutMapping(value="/feed")
	public ResponseEntity<FeedResultDto> feedUpdate(FeedDto feedDto, MultipartHttpServletRequest request){
		
		FeedResultDto feedResultDto = feedService.feedUpdate(feedDto, request);
		if( feedResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<FeedResultDto>(feedResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<FeedResultDto>(feedResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	
	}
	
	
}
