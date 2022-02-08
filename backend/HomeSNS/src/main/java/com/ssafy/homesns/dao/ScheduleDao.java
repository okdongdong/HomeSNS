package com.ssafy.homesns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homesns.dto.ScheduleDto;

@Mapper
public interface ScheduleDao {
	
	// groupId에 스케줄 추가하기
	public int scheduleCreate(ScheduleDto scheduleDto);
	
	// groupID를 사용해서 해당 그룹 내의 스케줄 목록 찾기
	public List<ScheduleDto> scheduleSearch(int groupId);
	
	// grouopID를 사용해서 해당 그룹 내의 스케줄 전체 개수 찾기
	public int scheduleTotalCount(int groupId);
	
	// 특정 스케줄 수정하기
	public int scheduleUpdate(ScheduleDto scheduleDto);
	
	// 특정 스케줄 삭제하기
	public int scheduleDelete(int scheduleId);
}
