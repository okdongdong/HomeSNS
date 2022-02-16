package com.ssafy.homesns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homesns.dto.HistoryTravelResultDto;
import com.ssafy.homesns.service.HistoryTravelService;

@CrossOrigin(
		origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500", "http://192.168.0.40:5500","https://i6e205.p.ssafy.io" },

		allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })

@RestController
@RequestMapping(value="/api")
public class HistoryTravelController {

	
	@Autowired
	HistoryTravelService historyTravelService; 
	
	private static final int SUCCESS = 1; 
	
	
	// 그룹내 추억장소 여행 기능 구현 
	// feedId, 사진 URL , 위치 정보 리턴
	@GetMapping(value="/location/{groupId}")
	public ResponseEntity<HistoryTravelResultDto> locationFeedList(@PathVariable(value="groupId") int groupId){
		
		HistoryTravelResultDto historyTravelResultDto = historyTravelService.groupFeedList(groupId);
		
		if( historyTravelResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<HistoryTravelResultDto>(historyTravelResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<HistoryTravelResultDto>(historyTravelResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
