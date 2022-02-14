package com.ssafy.homesns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.homesns.dto.ScheduleDto;
import com.ssafy.homesns.dto.ScheduleResultDto;
import com.ssafy.homesns.service.ScheduleService;
@CrossOrigin(
		origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500", "http://192.168.0.40:5500","https://i6e205.p.ssafy.io" },
		allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })

@RestController
@RequestMapping(value="/api")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;

	private static final int SUCCESS = 1;

	@PostMapping(value="/schedule")
	public ResponseEntity<ScheduleResultDto> scheduleCreate(@RequestBody ScheduleDto scheduleDto) {

		System.out.println(scheduleDto);

		ScheduleResultDto scheduleResultDto = scheduleService.scheduleCreate(scheduleDto);

		if ( scheduleResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<ScheduleResultDto>(scheduleResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<ScheduleResultDto>(scheduleResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value="/schedule/{groupId}")
	public ResponseEntity<ScheduleResultDto> scheduleSearch(@PathVariable(value="groupId") int groupId) {

		ScheduleResultDto scheduleResultDto = scheduleService.scheduleSearch(groupId);

		if ( scheduleResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<ScheduleResultDto>(scheduleResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<ScheduleResultDto>(scheduleResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/schedule/detail/{scheduleId}")
	public ResponseEntity<ScheduleResultDto> scheduleDetailSearch(@PathVariable(value="scheduleId") int scheduleId) {
		
		ScheduleResultDto scheduleResultDto = scheduleService.scheduleDetailSearch(scheduleId);
		
		if (scheduleResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<ScheduleResultDto>(scheduleResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<ScheduleResultDto>(scheduleResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping(value="/schedule")
	public ResponseEntity<ScheduleResultDto> scheduleUpdate(@RequestBody ScheduleDto scheduleDto) {
		
		ScheduleResultDto scheduleResultDto = scheduleService.scheduleUpdate(scheduleDto);

		if ( scheduleResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<ScheduleResultDto>(scheduleResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<ScheduleResultDto>(scheduleResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/schedule/{scheduleId}")
	public ResponseEntity<ScheduleResultDto> scheduleDelete(@PathVariable(value="scheduleId") int scheduleId) {
		
		ScheduleResultDto scheduleResultDto = scheduleService.scheduleDelete(scheduleId);

		if ( scheduleResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<ScheduleResultDto>(scheduleResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<ScheduleResultDto>(scheduleResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}









