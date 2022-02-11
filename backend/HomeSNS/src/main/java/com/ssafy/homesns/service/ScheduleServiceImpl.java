package com.ssafy.homesns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.homesns.dao.ScheduleDao;
import com.ssafy.homesns.dto.ScheduleDto;
import com.ssafy.homesns.dto.ScheduleResultDto;

@Service
public class ScheduleServiceImpl implements ScheduleService{

	
	@Autowired
	ScheduleDao scheduleDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	// 스케줄 추가하기
	@Override
	public ScheduleResultDto scheduleCreate(ScheduleDto scheduleDto) {
		
		ScheduleResultDto scheduleResultDto = new ScheduleResultDto();
		
		if ( scheduleDao.scheduleCreate(scheduleDto) == 1 ) {
			scheduleResultDto.setScheduleId(scheduleDto.getScheduleId());
			scheduleResultDto.setResult(SUCCESS);
		} else {
			scheduleResultDto.setResult(FAIL);
		}
		
		return scheduleResultDto;
	}

	// 스케줄 목록 찾기
	@Override
	public ScheduleResultDto scheduleSearch(int groupId) {
		ScheduleResultDto scheduleResultDto = new ScheduleResultDto();
		
		try {
			List<ScheduleDto> list = scheduleDao.scheduleSearch(groupId);
			int count = scheduleDao.scheduleTotalCount(groupId);
			
			scheduleResultDto.setScheduleDtoList(list);
			scheduleResultDto.setCount(count);
			scheduleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			scheduleResultDto.setResult(FAIL);
		}
		
		return scheduleResultDto;
	}


	// 스케줄 상세 조회
	@Override
	public ScheduleResultDto scheduleDetailSearch(int scheduleId) {
		
		ScheduleResultDto scheduleResultDto = new ScheduleResultDto();
		
		try {
			scheduleResultDto.setScheduleDto(scheduleDao.scheduleDetailSearch(scheduleId));
			scheduleResultDto.setResult(SUCCESS);
		} catch( Exception e) {
			e.printStackTrace();
			scheduleResultDto.setResult(FAIL);
		}
		
		return scheduleResultDto;
	}
	
	
	// 스케줄 수정하기
	@Override
	public ScheduleResultDto scheduleUpdate(ScheduleDto scheduleDto) {

		ScheduleResultDto scheduleResultDto = new ScheduleResultDto();
		
		if ( scheduleDao.scheduleUpdate(scheduleDto) == 1 ) {
			scheduleResultDto.setResult(SUCCESS);
		} else {
			scheduleResultDto.setResult(FAIL);
		}
		
		return scheduleResultDto;
	}

	// 스케줄 삭제하기
	@Override
	public ScheduleResultDto scheduleDelete(int scheduleId) {

		ScheduleResultDto scheduleResultDto = new ScheduleResultDto();
		
		if ( scheduleDao.scheduleDelete(scheduleId) == 1 ) {
			scheduleResultDto.setResult(SUCCESS);
		} else {
			scheduleResultDto.setResult(FAIL);
		}
		
		return scheduleResultDto;
	}

}
