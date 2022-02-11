package com.ssafy.homesns.service;

import com.ssafy.homesns.dto.ScheduleDto;
import com.ssafy.homesns.dto.ScheduleResultDto;

public interface ScheduleService {
	
	// 스케줄 추가하기
	public ScheduleResultDto scheduleCreate(ScheduleDto scheduleDto);
	// 스케줄 목록 찾기
	public ScheduleResultDto scheduleSearch(int groupId);
	// 스케줄 상세 조회
	public ScheduleResultDto scheduleDetailSearch(int scheduleId);
	// 스케줄 수정하기
	public ScheduleResultDto scheduleUpdate(ScheduleDto scheduleDto);
	// 스케줄 삭제하기
	public ScheduleResultDto scheduleDelete(int scheduleId);
}
